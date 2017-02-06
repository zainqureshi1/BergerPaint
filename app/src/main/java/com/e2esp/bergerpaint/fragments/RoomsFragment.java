package com.e2esp.bergerpaint.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ViewFlipper;

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
                selectRoom(room);
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

        for (int i = 0; i < 3; i++) {
            walls = new ArrayList<>();
            walls.add(new Wall("Front Wall", R.drawable.bedroom_1_1));
            walls.add(new Wall("Side Wall", R.drawable.bedroom_1_2));
            arrayListRooms.add(new Room("Bedroom", R.drawable.bedroom_1, walls));

            walls = new ArrayList<>();
            walls.add(new Wall("Front Wall", R.drawable.kitchen_1_1));
            arrayListRooms.add(new Room("Kitchen", R.drawable.kitchen_1, walls));

            walls = new ArrayList<>();
            walls.add(new Wall("Front Wall", R.drawable.kitchen_2_1));
            arrayListRooms.add(new Room("Kitchen", R.drawable.kitchen_2, walls));

            walls = new ArrayList<>();
            walls.add(new Wall("Front Wall", R.drawable.bedroom_2_1));
            arrayListRooms.add(new Room("Bedroom", R.drawable.bedroom_2, walls));
        }

        roomRecyclerAdapter.notifyDataSetChanged();

        selectRoom(arrayListRooms.get(0));
    }

    private void selectRoom(Room room) {
        for (int i = 0; i < arrayListRooms.size(); i++) {
            arrayListRooms.get(i).setSelected(false);
        }
        selectedRoom = room;
        selectedRoom.setSelected(true);
        roomRecyclerAdapter.notifyDataSetChanged();

        onFragmentInteractionListener.onInteraction(OnFragmentInteractionListener.ROOM_SELECTED, selectedRoom);
    }

    private void nextClicked() {
        onFragmentInteractionListener.onInteraction(OnFragmentInteractionListener.ROOMS_NEXT_CLICK, selectedRoom);
    }

}
