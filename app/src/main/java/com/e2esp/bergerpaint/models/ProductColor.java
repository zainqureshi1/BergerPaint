package com.e2esp.bergerpaint.models;

import java.util.ArrayList;

/**
 * Created by Zain on 2/1/2017.
 */

public class ProductColor {
    private String name;
    private String description;
    private String link;
    private int imageRes;

    private ArrayList<SecondaryColor> colors;

    public ProductColor(String name, String description, String link, int imageRes, ArrayList<SecondaryColor> colors) {
        this.name = name;
        this.description = description;
        this.link = link;
        this.imageRes = imageRes;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }

    public int getImageRes() {
        return imageRes;
    }

}
