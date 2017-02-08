package com.e2esp.bergerpaint.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.e2esp.bergerpaint.R;
import com.e2esp.bergerpaint.interfaces.OnTraysColorClickListener;
import com.e2esp.bergerpaint.models.PrimaryColor;
import com.e2esp.bergerpaint.models.SecondaryColor;

import java.util.ArrayList;

/**
 * Created by Zain on 1/31/2017.
 */

public class ColorsTrayRecyclerAdapter extends RecyclerView.Adapter<ColorsTrayRecyclerAdapter.ColorsViewHolder> {

    private Context context;
    private ArrayList<PrimaryColor> colorsList;
    private OnTraysColorClickListener onColorClickListener;

    private int boxSize;
    private int boxMargin;

    public ColorsTrayRecyclerAdapter(Context context, ArrayList<PrimaryColor> colorsList, OnTraysColorClickListener onColorClickListener) {
        this.context = context;
        this.colorsList = colorsList;
        this.onColorClickListener = onColorClickListener;

        this.boxSize = context.getResources().getDimensionPixelSize(R.dimen.color_box_size);
        this.boxMargin = context.getResources().getDimensionPixelSize(R.dimen.margin_small);
    }

    @Override
    public ColorsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.tray_item_color_layout, parent, false);
        return new ColorsViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return colorsList.size();
    }

    @Override
    public void onBindViewHolder(ColorsViewHolder holder, int position) {
        holder.bindView(colorsList.get(position));
    }

    public class ColorsViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageViewPrimaryColor;
        private HorizontalScrollView scrollViewSecondaryColors;
        private LinearLayout linearLayoutSecondaryColors;

        public ColorsViewHolder(View itemView) {
            super(itemView);
            imageViewPrimaryColor = (ImageView) itemView.findViewById(R.id.imageViewPrimaryColor);
            scrollViewSecondaryColors = (HorizontalScrollView) itemView.findViewById(R.id.scrollViewSecondaryColors);
            linearLayoutSecondaryColors = (LinearLayout) itemView.findViewById(R.id.linearLayoutSecondaryColors);
        }

        public void bindView(final PrimaryColor primaryColor) {
            imageViewPrimaryColor.setColorFilter(primaryColor.getColor());
            imageViewPrimaryColor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onColorClickListener.onPrimaryColorClick(primaryColor);
                }
            });

            linearLayoutSecondaryColors.removeAllViews();
            if (primaryColor.isTrayOpen()) {
                ArrayList<SecondaryColor> secondaryColors = primaryColor.getSecondaryColors();
                for (int i = 0; i < secondaryColors.size() ; i++) {
                    final SecondaryColor secondaryColor = secondaryColors.get(i);

                    ImageView imageView = new ImageView(context);
                    imageView.setVisibility(View.GONE);
                    imageView.setImageResource(R.drawable.color_box);
                    imageView.setColorFilter(secondaryColor.getColor());
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onColorClickListener.onSecondaryColorClick(secondaryColor);
                        }
                    });

                    LinearLayout.LayoutParams imaegViewParams = new LinearLayout.LayoutParams(boxSize, boxSize);
                    imaegViewParams.setMargins(boxMargin, boxMargin, boxMargin, boxMargin);
                    linearLayoutSecondaryColors.addView(imageView, imaegViewParams);
                }
                showSecondaryColors(0);
            }
        }

        private void showSecondaryColors(final int iteration) {
            if (iteration >= linearLayoutSecondaryColors.getChildCount()) {
                return;
            }
            linearLayoutSecondaryColors.getChildAt(iteration).setVisibility(View.VISIBLE);
            linearLayoutSecondaryColors.post(new Runnable() {
                @Override
                public void run() {
                    scrollViewSecondaryColors.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
                    showSecondaryColors(iteration + 1);
                }
            });
        }

    }
}
