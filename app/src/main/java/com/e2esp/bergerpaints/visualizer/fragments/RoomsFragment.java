package com.e2esp.bergerpaints.visualizer.fragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.e2esp.bergerpaints.visualizer.R;
import com.e2esp.bergerpaints.visualizer.adapters.GridSpacingItemDecoration;
import com.e2esp.bergerpaints.visualizer.adapters.RoomRecyclerAdapter;
import com.e2esp.bergerpaints.visualizer.interfaces.OnFragmentInteractionListener;
import com.e2esp.bergerpaints.visualizer.interfaces.OnRoomClickListener;
import com.e2esp.bergerpaints.visualizer.models.Room;
import com.e2esp.bergerpaints.visualizer.models.Wall;
import com.e2esp.bergerpaints.visualizer.utils.Utility;

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
        ImageView imageViewNext = (ImageView) view.findViewById(R.id.imageViewNext);
        imageViewNext.setOnClickListener(new View.OnClickListener() {
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
        walls.add(new Wall("Front Wall", R.drawable.room_01_01, R.drawable.room_01_01_00, Color.parseColor("#6B333F")));
        walls.add(new Wall("Side Wall", R.drawable.room_01_02, R.drawable.room_01_02_00, Color.parseColor("#6B333F")));
        walls.add(new Wall("Edge Walls", R.drawable.room_01_03, R.drawable.room_01_03_00, Color.parseColor("#DDD6C6")));
        walls.add(new Wall("Ceiling", R.drawable.room_01_04, R.drawable.room_01_04_00, Color.parseColor("#DBD9BC")));
        arrayListRooms.add(new Room("Bedroom", R.drawable.room_01, R.drawable.room_01_00, walls));

        walls = new ArrayList<>();
        walls.add(new Wall("Front Wall", R.drawable.room_02_01, R.drawable.room_02_01_00, Color.parseColor("#516563")));
        walls.add(new Wall("Left Wall", R.drawable.room_02_02, R.drawable.room_02_02_00, Color.parseColor("#A8767D")));
        arrayListRooms.add(new Room("Living Room", R.drawable.room_02, R.drawable.room_02_00, walls));

        walls = new ArrayList<>();
        walls.add(new Wall("Front Wall", R.drawable.room_03_01, R.drawable.room_03_01_00, Color.parseColor("#E3AAE1")));
        walls.add(new Wall("Right Wall", R.drawable.room_03_02, R.drawable.room_03_02_00, Color.parseColor("#823A83")));
        walls.add(new Wall("Left Wall", R.drawable.room_03_03, R.drawable.room_03_03_00, Color.parseColor("#4A1A4A")));
        walls.add(new Wall("Back Wall", R.drawable.room_03_04, R.drawable.room_03_04_00, Color.parseColor("#A967AF")));
        walls.add(new Wall("Ceiling", R.drawable.room_03_05, R.drawable.room_03_05_00, Color.parseColor("#F8F2F4")));
        arrayListRooms.add(new Room("Living Room", R.drawable.room_03, R.drawable.room_03_00, walls));

        walls = new ArrayList<>();
        walls.add(new Wall("Front Wall", R.drawable.room_04_01, R.drawable.room_04_01_00, Color.parseColor("#2D3B67")));
        walls.add(new Wall("Left Wall", R.drawable.room_04_02, R.drawable.room_04_02_00, Color.parseColor("#006B9C")));
        walls.add(new Wall("Right Wall", R.drawable.room_04_03, R.drawable.room_04_03_00, Color.parseColor("#006B9C")));
        walls.add(new Wall("Ceiling", R.drawable.room_04_04, R.drawable.room_04_04_00, Color.parseColor("#C1C1C2")));
        arrayListRooms.add(new Room("Kids Room", R.drawable.room_04, R.drawable.room_04_00, walls));

        walls = new ArrayList<>();
        walls.add(new Wall("Front Wall", R.drawable.room_05_01, R.drawable.room_05_01_00, Color.parseColor("#D59F57")));
        walls.add(new Wall("Ceiling", R.drawable.room_05_02, R.drawable.room_05_02_00, Color.parseColor("#9C7C71")));
        arrayListRooms.add(new Room("Small Bedroom", R.drawable.room_05, R.drawable.room_05_00, walls));

        walls = new ArrayList<>();
        walls.add(new Wall("Front Wall", R.drawable.room_06_01, R.drawable.room_06_01_00, Color.parseColor("#E18F21")));
        walls.add(new Wall("Left Wall", R.drawable.room_06_02, R.drawable.room_06_02_00, Color.parseColor("#E18F21")));
        walls.add(new Wall("Pillar", R.drawable.room_06_03, R.drawable.room_06_03_00, Color.parseColor("#E6C7B5")));
        walls.add(new Wall("Ceiling", R.drawable.room_06_04, R.drawable.room_06_04_00, Color.parseColor("#C38F6E")));
        arrayListRooms.add(new Room("Living Room", R.drawable.room_06, R.drawable.room_06_00, walls));

        walls = new ArrayList<>();
        walls.add(new Wall("Left Wall", R.drawable.room_07_01, R.drawable.room_07_01_00, Color.parseColor("#BF5626")));
        walls.add(new Wall("Front Wall", R.drawable.room_07_02, R.drawable.room_07_02_00, Color.parseColor("#FAF2EF")));
        walls.add(new Wall("Ceiling", R.drawable.room_07_03, R.drawable.room_07_03_00, Color.parseColor("#BF5626")));
        arrayListRooms.add(new Room("TV Lounge", R.drawable.room_07, R.drawable.room_07_00, walls));

        walls = new ArrayList<>();
        walls.add(new Wall("Right Wall", R.drawable.room_08_01, R.drawable.room_08_01_00, Color.parseColor("#CDC2BC")));
        walls.add(new Wall("Front Wall", R.drawable.room_08_02, R.drawable.room_08_02_00, Color.parseColor("#7F3300")));
        walls.add(new Wall("Left Wall", R.drawable.room_08_03, R.drawable.room_08_03_00, Color.parseColor("#CDC2BC")));
        walls.add(new Wall("Ceiling", R.drawable.room_08_04, R.drawable.room_08_04_00, Color.parseColor("#B4A5A0")));
        arrayListRooms.add(new Room("Gallery", R.drawable.room_08, R.drawable.room_08_00, walls));

        walls = new ArrayList<>();
        walls.add(new Wall("Upper Floor", R.drawable.room_09_01, R.drawable.room_09_01_00, Color.parseColor("#CCB99C")));
        walls.add(new Wall("Lower Floor", R.drawable.room_09_02, R.drawable.room_09_02_00, Color.parseColor("#CCB99C")));
        arrayListRooms.add(new Room("Bungalow Exterior", R.drawable.room_09, R.drawable.room_09_00, walls));

        walls = new ArrayList<>();
        walls.add(new Wall("Back Right Section", R.drawable.room_10_01, R.drawable.room_10_01_00, Color.parseColor("#99A1AE")));
        walls.add(new Wall("Back Left Section", R.drawable.room_10_02, R.drawable.room_10_02_00, Color.parseColor("#99A1AE")));
        walls.add(new Wall("Front Right Section", R.drawable.room_10_03, R.drawable.room_10_03_00, Color.parseColor("#525563")));
        walls.add(new Wall("Front Left Section", R.drawable.room_10_04, R.drawable.room_10_04_00, Color.parseColor("#525563")));
        arrayListRooms.add(new Room("House Exterior", R.drawable.room_10, R.drawable.room_10_00, walls));

        // Single wall images not good enough, says client
        /*walls = new ArrayList<>();
        walls.add(new Wall("Front Wall", R.drawable.room_11_01, R.drawable.room_11_01_00, Color.parseColor("#665744")));
        arrayListRooms.add(new Room("Open Bedroom", R.drawable.room_11, R.drawable.room_11_00, walls));

        walls = new ArrayList<>();
        walls.add(new Wall("Back Wall", R.drawable.room_12_01, R.drawable.room_12_01_00, Color.parseColor("#C25627")));
        arrayListRooms.add(new Room("Bedroom", R.drawable.room_12, R.drawable.room_12_00, walls));

        walls = new ArrayList<>();
        walls.add(new Wall("Front Wall", R.drawable.room_13_01, R.drawable.room_13_01_00, Color.parseColor("#B72926")));
        arrayListRooms.add(new Room("TV Lounge", R.drawable.room_13, R.drawable.room_13_00, walls));

        walls = new ArrayList<>();
        walls.add(new Wall("Side Wall", R.drawable.room_14_01, R.drawable.room_14_01_00, Color.parseColor("#B2AF02")));
        arrayListRooms.add(new Room("Open Bedroom", R.drawable.room_14, R.drawable.room_14_00, walls));*/

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
