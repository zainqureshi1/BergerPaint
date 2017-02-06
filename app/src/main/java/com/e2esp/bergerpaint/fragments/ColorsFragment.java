package com.e2esp.bergerpaint.fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.e2esp.bergerpaint.R;
import com.e2esp.bergerpaint.adapters.ColorsTrayRecyclerAdapter;
import com.e2esp.bergerpaint.adapters.WallsTrayRecyclerAdapter;
import com.e2esp.bergerpaint.interfaces.OnFragmentInteractionListener;
import com.e2esp.bergerpaint.interfaces.OnTraysColorClickListener;
import com.e2esp.bergerpaint.interfaces.OnTraysWallClickListener;
import com.e2esp.bergerpaint.interfaces.OnWallImageTouchListener;
import com.e2esp.bergerpaint.models.PrimaryColor;
import com.e2esp.bergerpaint.models.Room;
import com.e2esp.bergerpaint.models.SecondaryColor;
import com.e2esp.bergerpaint.models.Wall;

import java.util.ArrayList;

public class ColorsFragment extends Fragment {

    private static ColorsFragment latestInstance;

    private AppCompatTextView textViewChooseWall;
    private AppCompatTextView textViewChooseColor;

    private RelativeLayout relativeLayoutPictureContainer;

    private RecyclerView recyclerViewWallsTray;
    private ArrayList<Wall> wallsList;
    private WallsTrayRecyclerAdapter wallsRecyclerAdapter;

    private RecyclerView recyclerViewColorsTray;
    private ArrayList<PrimaryColor> allColorsList;
    private ArrayList<PrimaryColor> activeColorsList;
    private ColorsTrayRecyclerAdapter colorsRecyclerAdapter;

    private Room room;

    private ImageView selectedWallImage;
    private SecondaryColor selectedColor;

    private OnFragmentInteractionListener onFragmentInteractionListener;

    private boolean wallsTrayVisible;
    private boolean colorsTrayVisible;

    public ColorsFragment() {
        // Required empty public constructor
    }

    public static ColorsFragment newInstance() {
        latestInstance = new ColorsFragment();
        return latestInstance;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            onFragmentInteractionListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onFragmentInteractionListener = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_colors, container, false);

        setupViews(view);
        setupColorsTray();

        return view;
    }

    private void setupViews(View view) {
        textViewChooseWall = (AppCompatTextView) view.findViewById(R.id.textViewChooseWall);
        textViewChooseWall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (wallsTrayVisible) {
                    hideWallsTray();
                } else {
                    showWallsTray();
                }
            }
        });
        textViewChooseColor = (AppCompatTextView) view.findViewById(R.id.textViewChooseColor);
        textViewChooseColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (colorsTrayVisible) {
                    hideColorsTray();
                } else {
                    showColorsTray();
                }
            }
        });

        relativeLayoutPictureContainer = (RelativeLayout) view.findViewById(R.id.relativeLayoutPictureContainer);

        recyclerViewWallsTray = (RecyclerView) view.findViewById(R.id.recyclerViewWallsTray);
        wallsList = new ArrayList<>();
        wallsRecyclerAdapter = new WallsTrayRecyclerAdapter(getContext(), wallsList, new OnTraysWallClickListener() {
            @Override
            public void onWallClick(Wall wall) {
                wallClicked(wall);
            }
        });
        recyclerViewWallsTray.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerViewWallsTray.setAdapter(wallsRecyclerAdapter);

        recyclerViewColorsTray = (RecyclerView) view.findViewById(R.id.recyclerViewColorsTray);
        allColorsList = new ArrayList<>();
        activeColorsList = new ArrayList<>();
        colorsRecyclerAdapter = new ColorsTrayRecyclerAdapter(getContext(), activeColorsList, new OnTraysColorClickListener() {
            @Override
            public void onPrimaryColorClick(PrimaryColor color) {
                primaryColorClicked(color);
            }
            @Override
            public void onSecondaryColorClick(SecondaryColor color) {
                secondaryColorClicked(color);
            }
        });
        recyclerViewColorsTray.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerViewColorsTray.setAdapter(colorsRecyclerAdapter);

        AppCompatTextView textViewNext = (AppCompatTextView) view.findViewById(R.id.textViewNext);
        textViewNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextClicked();
            }
        });

        if (room == null) {
            // Add default room
            ArrayList<Wall> walls = new ArrayList<>();
            walls.add(new Wall("Front Wall", R.drawable.bedroom_1_1));
            walls.add(new Wall("Side Wall", R.drawable.bedroom_1_2));
            setRoom(new Room("Bedroom", R.drawable.bedroom_1, walls));
        }
    }

    private void nextClicked() {
        onFragmentInteractionListener.onInteraction(OnFragmentInteractionListener.COLORS_NEXT_CLICK, selectedColor);
    }

    private void showWallsTray() {
        if (room == null) {
            return;
        }
        hideColorsTray();
        wallsTrayVisible = true;
        recyclerViewWallsTray.bringToFront();
        showWallsTray(0);
    }

    private void showWallsTray(final int iteration) {
        if (iteration >= room.getWallsList().size() || !wallsTrayVisible) {
            return;
        }
        wallsList.add(room.getWallsList().get(iteration).clone());
        wallsRecyclerAdapter.notifyDataSetChanged();
        recyclerViewWallsTray.post(new Runnable() {
            @Override
            public void run() {
                showWallsTray(iteration + 1);
            }
        });
    }

    private void hideWallsTray() {
        wallsTrayVisible = false;
        wallsList.clear();
        wallsRecyclerAdapter.notifyDataSetChanged();
        relativeLayoutPictureContainer.bringToFront();
    }

    private void wallClicked(Wall wall) {
        hideWallsTray();
        textViewChooseWall.setText(wall.getName());
        selectedWallImage = wall.getWallImage();
        updateWallColor();
    }

    private void setupColorsTray() {
        allColorsList.add(new PrimaryColor(getResources().getColor(R.color.white), "White", true));
        allColorsList.add(new PrimaryColor(getResources().getColor(R.color.red), "Red", true));
        allColorsList.add(new PrimaryColor(getResources().getColor(R.color.green), "Green", true));
        allColorsList.add(new PrimaryColor(getResources().getColor(R.color.blue), "Blue", true));
        allColorsList.add(new PrimaryColor(getResources().getColor(R.color.yellow), "Yellow", true));
        allColorsList.add(new PrimaryColor(getResources().getColor(R.color.cyan), "Cyan", true));
        allColorsList.add(new PrimaryColor(getResources().getColor(R.color.pink), "Pink", true));
        allColorsList.add(new PrimaryColor(getResources().getColor(R.color.black), "Black", true));

        selectedColor = allColorsList.get(0);
    }

    private void showColorsTray() {
        hideWallsTray();
        colorsTrayVisible = true;
        recyclerViewColorsTray.bringToFront();
        showColorsTray(0);
    }

    private void showColorsTray(final int iteration) {
        if (iteration >= allColorsList.size() || !colorsTrayVisible) {
            return;
        }
        activeColorsList.add(allColorsList.get(iteration).clone());
        colorsRecyclerAdapter.notifyDataSetChanged();
        recyclerViewColorsTray.post(new Runnable() {
            @Override
            public void run() {
                showColorsTray(iteration + 1);
            }
        });
    }

    private void hideColorsTray() {
        colorsTrayVisible = false;
        activeColorsList.clear();
        colorsRecyclerAdapter.notifyDataSetChanged();
        relativeLayoutPictureContainer.bringToFront();
    }

    private void primaryColorClicked(PrimaryColor color) {
        if (color.isTrayOpen()) {
            secondaryColorClicked(color);
            return;
        }
        for (int i = 0; i < activeColorsList.size(); i++) {
            activeColorsList.get(i).setTrayOpen(false);
        }
        color.setTrayOpen(true);
        colorsRecyclerAdapter.notifyDataSetChanged();
    }

    private void secondaryColorClicked(SecondaryColor color) {
        hideColorsTray();
        textViewChooseColor.setTextColor(color.getColor());
        selectedColor = color;
        updateWallColor();

        onFragmentInteractionListener.onInteraction(OnFragmentInteractionListener.COLOR_SELECTED, selectedColor);
    }

    private void updateWallColor() {
        if (selectedWallImage != null && selectedColor != null) {
            selectedWallImage.setColorFilter(selectedColor.getColor());
        }
    }

    //private int[] roomViewCoords;
    //private int[] roomViewSize;
    private void setRoom(Room room) {
        if (relativeLayoutPictureContainer == null) {
            return;
        }
        this.room = room;

        relativeLayoutPictureContainer.removeAllViews();

        addRoomImage(room.getImageRes(), room.getName());

        ArrayList<Wall> walls = room.getWallsList();
        if (walls != null && walls.size() > 0) {
            for (int i = 0; i < walls.size(); i++) {
                Wall wall = walls.get(i);
                ImageView wallImage = addRoomImage(wall.getImageRes(), wall.getName());
                wall.setWallImage(wallImage, onWallTouchListener);
                if (i == 0) {
                    selectedWallImage = wallImage;
                }
            }
        }
        textViewChooseWall.setText(getString(R.string.choose_a_wall));
    }

    private ImageView addRoomImage(int resId, String tag) {
        ImageView imageViewRoom = new ImageView(getContext());
        imageViewRoom.setImageResource(resId);
        imageViewRoom.setTag(tag);

        RelativeLayout.LayoutParams layoutParamsRoom = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParamsRoom.addRule(RelativeLayout.CENTER_IN_PARENT);
        relativeLayoutPictureContainer.addView(imageViewRoom, layoutParamsRoom);

        return imageViewRoom;
    }

    private OnWallImageTouchListener onWallTouchListener = new OnWallImageTouchListener() {
        @Override
        public void onWallTouch(Wall wall) {
            Log.i("Touch", "Touched wall: "+wall.getName());
            wallClicked(wall);
        }
    };

    public static void setSelectedRoom(Room room) {
        if (latestInstance != null) {
            latestInstance.setRoom(room);
        }
    }

    public static boolean backPressed() {
        if (latestInstance != null) {
            if (latestInstance.wallsTrayVisible) {
                latestInstance.hideWallsTray();
                return true;
            }
            if (latestInstance.colorsTrayVisible) {
                latestInstance.hideColorsTray();
                return true;
            }
        }
        return false;
    }

}
