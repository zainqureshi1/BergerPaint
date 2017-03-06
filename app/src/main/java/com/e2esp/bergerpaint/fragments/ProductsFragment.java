package com.e2esp.bergerpaint.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.e2esp.bergerpaint.R;
import com.e2esp.bergerpaint.adapters.ProductRecyclerAdapter;
import com.e2esp.bergerpaint.adapters.VerticalSpacingItemDecoration;
import com.e2esp.bergerpaint.interfaces.OnProductClickListener;
import com.e2esp.bergerpaint.models.Product;
import com.e2esp.bergerpaint.models.SecondaryColor;
import com.e2esp.bergerpaint.utils.Utility;

import java.util.ArrayList;

public class ProductsFragment extends Fragment {

    private static ProductsFragment latestInstance;

    private ArrayList<Product> arrayListProducts;
    private ProductRecyclerAdapter productRecyclerAdapter;

    private SecondaryColor selectedColor;

    public ProductsFragment() {
        // Required empty public constructor
    }

    public static ProductsFragment newInstance() {
        latestInstance = new ProductsFragment();
        return latestInstance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_products, container, false);

        setupView(view);
        loadProducts();

        return view;
    }

    private void setupView(View view) {
        RecyclerView recyclerViewProducts = (RecyclerView) view.findViewById(R.id.recyclerViewProducts);
        arrayListProducts = new ArrayList<>();
        int color = selectedColor != null ? selectedColor.getColor() : getResources().getColor(R.color.white);
        productRecyclerAdapter = new ProductRecyclerAdapter(getContext(), arrayListProducts, color, new OnProductClickListener() {
            @Override
            public void onBuyClick(Product product) {
                buyClicked(product);
            }
            @Override
            public void onLearnMoreClick(Product product) {
                learnMoreClicked(product);
            }
        });
        recyclerViewProducts.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerViewProducts.addItemDecoration(new VerticalSpacingItemDecoration(Utility.dpToPx(getContext(), 10)));
        //recyclerViewProducts.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        //recyclerViewProducts.addItemDecoration(new GridSpacingItemDecoration(2, Utility.dpToPx(getContext(), 10), true));
        recyclerViewProducts.setItemAnimator(new DefaultItemAnimator());
        recyclerViewProducts.setAdapter(productRecyclerAdapter);
    }

    private void loadProducts() {
        arrayListProducts.clear();

        arrayListProducts.add(new Product("Silk Emulsion", "Berger Silk Emulsion is a high quality acrylic emulsion. It has hiding power and color retention properties. It is a low odor emulsion with high stain resistance, outstanding water resistance, anti-fungal properties, excellent coverage and outstanding washability. It is a premium quality decorative paint which gives an attractive silk luster.", "http://berger.com.pk/business-lines/decorative-business/interiors/silk-emulsion/", R.drawable.berger_silk_emulsion));
        arrayListProducts.add(new Product("Elegance Matt Emulsion", "Berger Robbialac Matt Emulsion is top quality acrylic-based emulsion paint, ideal for interior surfaces. It dries out to a smooth finish which is durable and easily washable.", "http://berger.com.pk/business-lines/decorative-business/interiors/elegance-matt-emulsion/", R.drawable.berger_elegance_matt_emulsion));
        //arrayListProducts.add(new Product("VIP Plastic Emulsion", "VIP Plastic Emulsion is a top quality plastic emulsion for walls, ceilings, old and new cement concrete plasters, chipboard and hardboard.", "http://berger.com.pk/business-lines/decorative-business/interiors/vip-plastic-emulsion/", R.drawable.berger_vip_plastic_emulsion));
        arrayListProducts.add(new Product("VIP Super Gloss Enamel", "VIP Super Gloss Enamel is a top quality synthetic enamel coating for interior and exterior use. It may be applied on prepared primed surfaces such as wood, metal, cement plaster, asbestos, cement sheets and hard board, etc.", "http://berger.com.pk/business-lines/decorative-business/wood-metal/vip-super-gloss-enamel/", R.drawable.berger_vip_super_gloss_enamel));
        arrayListProducts.add(new Product("Easy Clean Emulsion", "Eazy Clean Emulsion is a good quality washable vinyl emulsion paint which can be wiped clean by damp cloth.", "http://berger.com.pk/business-lines/decorative-business/interiors/eazy-clean-emulsion/", R.drawable.berger_easy_clean_emulsion));
        arrayListProducts.add(new Product("New SPD Smooth Emulsion", "SPD is a top quality smooth emulsion. It can be used on walls, ceilings, old and new cement, concrete, plasters, chipboard and hardboard.", "http://berger.com.pk/business-lines/decorative-business/interiors/spd-smooth-emulsion/", R.drawable.berger_new_spd_smooth_emulsion));
        arrayListProducts.add(new Product("All Rounder Matt Enamel", "Allrounder is a high quality matt finish enamel for walls, ceilings, old and new cement plasters, woodwork, metals, chipboard and hardboard. It is tough and long-lasting and is specially recommended for kitchens, bathrooms, corridors and staircase walls, etc.", "http://berger.com.pk/business-lines/decorative-business/wood-metal/allrounder-matt-enamel/", R.drawable.berger_allrounder_matt_enamel));
        arrayListProducts.add(new Product("Weathercoat Acrylic Exterior Finish", "Weathercoat is a smooth water based masonry exterior paint. It contains tough flexible resin pigmented with titanium dioxide and light fast pigments. Its smooth finish has the highest degree of durability and is resistant to all types of weather conditions.", "http://berger.com.pk/business-lines/decorative-business/exteriors/weathercoat-acrylic-exterior-finish/", R.drawable.berger_weathercoat_acrylic_exterior_finish));

        productRecyclerAdapter.notifyDataSetChanged();
    }

    private void buyClicked(Product product) {

    }

    private void learnMoreClicked(Product product) {
        openWebPage(product.getLink());
    }

    private void openWebPage (String link) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
        startActivity(browserIntent);
    }

    public void setColor(SecondaryColor color) {
        selectedColor = color;
        if (productRecyclerAdapter != null) {
            productRecyclerAdapter.setColor(selectedColor.getColor());
            productRecyclerAdapter.notifyDataSetChanged();
        }
    }

    public static void setSelectedColor(SecondaryColor color) {
        if (latestInstance != null) {
            latestInstance.setColor(color);
        }
    }

}
