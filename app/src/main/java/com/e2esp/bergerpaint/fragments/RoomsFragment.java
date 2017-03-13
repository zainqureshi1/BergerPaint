package com.e2esp.bergerpaint.fragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.e2esp.bergerpaint.R;
import com.e2esp.bergerpaint.adapters.GridSpacingItemDecoration;
import com.e2esp.bergerpaint.adapters.RoomRecyclerAdapter;
import com.e2esp.bergerpaint.interfaces.OnFragmentInteractionListener;
import com.e2esp.bergerpaint.interfaces.OnRoomClickListener;
import com.e2esp.bergerpaint.models.Room;
import com.e2esp.bergerpaint.models.Wall;
import com.e2esp.bergerpaint.utils.Utility;

import java.util.ArrayList;

/**
 * Created by Zain on 1/31/2017.
 */

public class RoomsFragment extends Fragment {

    private ArrayList<Room> arrayListRooms;
    private RoomRecyclerAdapter roomRecyclerAdapter;

    private Room selectedRoom;
    private OnFragmentInteractionListener onFragmentInteractionListener;

    public RoomsFragment() {
        // Required empty public constructor
    }

    public static RoomsFragment newInstance() {
        RoomsFragment fragment = new RoomsFragment();
        return fragment;
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
        View view = inflater.inflate(R.layout.fragment_rooms, container, false);

        setupView(view);
        loadRooms();

        return view;
    }

    private void setupView(View view) {
        AppCompatTextView textViewNext = (AppCompatTextView) view.findViewById(R.id.textViewNext);
        textViewNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextClicked();
            }
        });

        RecyclerView recyclerViewRooms = (RecyclerView) view.findViewById(R.id.recyclerViewRooms);
        arrayListRooms = new ArrayList<>();
        roomRecyclerAdapter = new RoomRecyclerAdapter(getContext(), arrayListRooms, new OnRoomClickListener() {
            @Override
            public void onRoomClick(Room room) {
                selectRoom(room, false);
            }
        });

        recyclerViewRooms.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerViewRooms.addItemDecoration(new GridSpacingItemDecoration(2, Utility.dpToPx(getContext(), 10), true));
        recyclerViewRooms.setItemAnimator(new DefaultItemAnimator());
        recyclerViewRooms.setAdapter(roomRecyclerAdapter);
    }

    private void loadRooms() {
        arrayListRooms.clear();

        ArrayList<Wall> walls;

        walls = new ArrayList<>();
        walls.add(new Wall("Front Wall", R.drawable.room_01_01, R.drawable.room_01_01_00, Color.parseColor("#665744")));
        arrayListRooms.add(new Room("Open Bedroom", R.drawable.room_01, R.drawable.room_01_00, walls));

        walls = new ArrayList<>();
        walls.add(new Wall("Back Wall", R.drawable.room_02_01, R.drawable.room_02_01_00, Color.parseColor("#C25627")));
        arrayListRooms.add(new Room("Bedroom", R.drawable.room_02, R.drawable.room_02_00, walls));

        walls = new ArrayList<>();
        walls.add(new Wall("Front Wall", R.drawable.room_03_01, R.drawable.room_03_01_00, Color.parseColor("#B72926")));
        arrayListRooms.add(new Room("TV Lounge", R.drawable.room_03, R.drawable.room_03_00, walls));

        walls = new ArrayList<>();
        walls.add(new Wall("Side Wall", R.drawable.room_04_01, R.drawable.room_04_01_00, Color.parseColor("#B2AF02")));
        arrayListRooms.add(new Room("Open Bedroom", R.drawable.room_04, R.drawable.room_04_00, walls));

        walls = new ArrayList<>();
        walls.add(new Wall("Front Wall", R.drawable.room_05_01, R.drawable.room_05_01_00, Color.parseColor("#D59F57")));
        walls.add(new Wall("Ceiling", R.drawable.room_05_02, R.drawable.room_05_02_00, Color.parseColor("#9C7C71")));
        arrayListRooms.add(new Room("Small Bedroom", R.drawable.room_05, R.drawable.room_05_00, walls));

        walls = new ArrayList<>();
        walls.add(new Wall("Front Wall", R.drawable.room_06_01, R.drawable.room_06_01_00, Color.parseColor("#6B333F")));
        walls.add(new Wall("Side Wall", R.drawable.room_06_02, R.drawable.room_06_02_00, Color.parseColor("#6B333F")));
        walls.add(new Wall("Edge Walls", R.drawable.room_06_03, R.drawable.room_06_03_00, Color.parseColor("#DDD6C6")));
        walls.add(new Wall("Ceiling", R.drawable.room_06_04, R.drawable.room_06_04_00, Color.parseColor("#DBD9BC")));
        arrayListRooms.add(new Room("Bedroom", R.drawable.room_06, R.drawable.room_06_00, walls));

        walls = new ArrayList<>();
        walls.add(new Wall("Front Wall", R.drawable.room_07_01, R.drawable.room_07_01_00, Color.parseColor("#2D3B67")));
        walls.add(new Wall("Left Wall", R.drawable.room_07_02, R.drawable.room_07_02_00, Color.parseColor("#006B9C")));
        walls.add(new Wall("Right Wall", R.drawable.room_07_03, R.drawable.room_07_03_00, Color.parseColor("#006B9C")));
        walls.add(new Wall("Ceiling", R.drawable.room_07_04, R.drawable.room_07_04_00, Color.parseColor("#C1C1C2")));
        arrayListRooms.add(new Room("Kids Room", R.drawable.room_07, R.drawable.room_07_00, walls));

        walls = new ArrayList<>();
        walls.add(new Wall("Front Wall", R.drawable.room_08_01, R.drawable.room_08_01_00, Color.parseColor("#516563")));
        walls.add(new Wall("Left Wall", R.drawable.room_08_02, R.drawable.room_08_02_00, Color.parseColor("#A8767D")));
        arrayListRooms.add(new Room("Living Room", R.drawable.room_08, R.drawable.room_08_00, walls));

        walls = new ArrayList<>();
        walls.add(new Wall("Upper Floor", R.drawable.room_09_01, R.drawable.room_09_01_00, Color.parseColor("#CCB99C")));
        walls.add(new Wall("Lower Floor", R.drawable.room_09_02, R.drawable.room_09_02_00, Color.parseColor("#CCB99C")));
        arrayListRooms.add(new Room("Bungalow Exterior", R.drawable.room_09, R.drawable.room_09_00, walls));

        walls = new ArrayList<>();
        walls.add(new Wall("Front Wall", R.drawable.room_10_01, R.drawable.room_10_01_00, Color.parseColor("#E1DECB")));
        walls.add(new Wall("Window Frames", R.drawable.room_10_02, R.drawable.room_10_02_00, Color.parseColor("#015C5B")));
        walls.add(new Wall("Roof", R.drawable.room_10_03, R.drawable.room_10_03_00, Color.parseColor("#015C5B")));
        arrayListRooms.add(new Room("House Exterior", R.drawable.room_10, R.drawable.room_10_00, walls));

        roomRecyclerAdapter.notifyDataSetChanged();

        selectRoom(arrayListRooms.get(0), true);
    }

    private void selectRoom(Room room, boolean def) {
        for (int i = 0; i < arrayListRooms.size(); i++) {
            arrayListRooms.get(i).setSelected(false);
        }
        selectedRoom = room;
        selectedRoom.setSelected(true);
        roomRecyclerAdapter.notifyDataSetChanged();

        onFragmentInteractionListener.onInteraction(def ? OnFragmentInteractionListener.DEFAULT_ROOM_SELECTED : OnFragmentInteractionListener.ROOM_SELECTED, selectedRoom);
    }

    private void nextClicked() {
        onFragmentInteractionListener.onInteraction(OnFragmentInteractionListener.ROOMS_NEXT_CLICK, selectedRoom);
    }

}
