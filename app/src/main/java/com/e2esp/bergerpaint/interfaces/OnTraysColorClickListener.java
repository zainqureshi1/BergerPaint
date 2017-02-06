package com.e2esp.bergerpaint.interfaces;

import com.e2esp.bergerpaint.models.PrimaryColor;
import com.e2esp.bergerpaint.models.SecondaryColor;

/**
 * Created by Zain on 1/31/2017.
 */

public interface OnTraysColorClickListener {
    void onPrimaryColorClick(PrimaryColor color);
    void onSecondaryColorClick(SecondaryColor color);
}
