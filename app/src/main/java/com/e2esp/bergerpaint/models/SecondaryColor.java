package com.e2esp.bergerpaint.models;

import android.content.Context;

/**
 * Created by Zain on 1/31/2017.
 */

public class SecondaryColor {

    private int color;
    private String name;

    public SecondaryColor(int color, String name) {
        this.color = color;
        this.name = name;
    }

    public int getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

}
