package com.e2esp.bergerpaint.models;

import java.util.ArrayList;

/**
 * Created by Zain on 1/30/2017.
 */

public class Room {
    private String name;
    private int imageRes;

    private ArrayList<Wall> wallsList;

    private boolean selected;

    public Room(String name, int imageRes) {
        this(name, imageRes, new ArrayList<Wall>());
    }

    public Room(String name, int imageRes, ArrayList<Wall> wallsList) {
        this.name = name;
        this.imageRes = imageRes;
        this.wallsList = wallsList;
        this.selected = false;
    }

    public String getName() {
        return name;
    }

    public int getImageRes() {
        return imageRes;
    }

    public ArrayList<Wall> getWallsList() {
        return wallsList;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

}
