package com.e2esp.bergerpaint.models;

import java.util.ArrayList;

/**
 * Created by Zain on 1/31/2017.
 */

public class PrimaryColor extends SecondaryColor {

    private ArrayList<SecondaryColor> secondaryColors;
    private boolean trayOpen;

    public PrimaryColor(int color, String name, ArrayList<SecondaryColor> secondaryColors) {
        super(color, name);
        this.secondaryColors = secondaryColors;
        this.trayOpen = false;
    }

    public PrimaryColor(int color, String name, boolean autoGenerateSecondaryColors) {
        super(color, name);
        this.secondaryColors = new ArrayList<>();
        if (autoGenerateSecondaryColors) {
            String hex = Integer.toHexString(color);
            String aHex = hex.substring(0, 2);
            int r = Integer.parseInt(hex.substring(2, 4),16);
            int g = Integer.parseInt(hex.substring(4, 6),16);
            int b = Integer.parseInt(hex.substring(6, 8),16);
            for (int i = 1; i <= 10; i++) {
                int r1 = r == 0 ? (r + i * 10) : r == 255 ? (r - i * 10) : r;
                int g1 = g == 0 ? (g + i * 10) : g == 255 ? (g - i * 10) : g;
                int b1 = b == 0 ? (b + i * 10) : b == 255 ? (b - i * 10) : b;
                String rHex = Integer.toHexString(r1);
                if (rHex.length() < 2) rHex = 0 + rHex;
                String gHex = Integer.toHexString(g1);
                if (gHex.length() < 2) gHex = 0 + gHex;
                String bHex = Integer.toHexString(b1);
                if (bHex.length() < 2) bHex = 0 + bHex;
                //String hex1 = String.format("%1$02d%2$02d%3$02d%4$02d", a, r1, g1, b1);
                String hex1 = aHex + rHex + gHex + bHex;
                this.secondaryColors.add(new SecondaryColor((int) Long.parseLong(hex1, 16), name + " " + i));
            }
        }
        this.trayOpen = false;
    }

    public ArrayList<SecondaryColor> getSecondaryColors() {
        return secondaryColors;
    }

    public boolean isTrayOpen() {
        return trayOpen;
    }

    public void setTrayOpen(boolean trayOpen) {
        this.trayOpen = trayOpen;
    }

    public PrimaryColor clone() {
        return new PrimaryColor(getColor(), getName(), new ArrayList<>(secondaryColors));
    }

}
