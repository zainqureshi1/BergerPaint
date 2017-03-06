package com.e2esp.bergerpaint.interfaces;

import com.e2esp.bergerpaint.models.PrimaryColor;
import com.e2esp.bergerpaint.models.ProductColor;

/**
 * Created by Zain on 1/31/2017.
 */

public interface OnTraysProductClickListener {
    void onPrimaryColorClick(PrimaryColor color);
    void onProductColorClick(ProductColor color);
}
