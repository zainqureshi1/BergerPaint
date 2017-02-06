package com.e2esp.bergerpaint.models;

/**
 * Created by Zain on 2/1/2017.
 */

public class Product {
    private String name;
    private String description;
    private String link;
    private int imageRes;

    public Product(String name, String description, String link, int imageRes) {
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
