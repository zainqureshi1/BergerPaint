package com.e2esp.bergerpaint.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.e2esp.bergerpaint.R;
import com.e2esp.bergerpaint.interfaces.OnTraysWallClickListener;
import com.e2esp.bergerpaint.models.SecondaryColor;
import com.e2esp.bergerpaint.models.Wall;

import java.util.ArrayList;

/**
 * Created by Zain on 2/1/2017.
 */

public class WallsTrayRecyclerAdapter extends RecyclerView.Adapter<WallsTrayRecyclerAdapter.WallViewHolder> {

    private Context context;
    private ArrayList<Wall> wallsList;
    private OnTraysWallClickListener onWallClickListener;

    public WallsTrayRecyclerAdapter(Context context, ArrayList<Wall> wallsList, OnTraysWallClickListener onWallClickListener) {
        this.context = context;
        this.wallsList = wallsList;
        this.onWallClickListener = onWallClickListener;
    }

    @Override
    public WallViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.tray_item_wall_layout, parent, false);
        return new WallViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return wallsList.size();
    }

    @Override
    public void onBindViewHolder(WallViewHolder holder, int position) {
        holder.bindView(wallsList.get(position));
    }

    public class WallViewHolder extends RecyclerView.ViewHolder {

        private View topView;
        private TextView textViewName;
        private TextView textViewColor;
        private TextView textViewCode;

        public WallViewHolder(View itemView) {
            super(itemView);
            topView = itemView;
            textViewName = (TextView) itemView.findViewById(R.id.textViewWallName);
            textViewColor = (TextView) itemView.findViewById(R.id.textViewColorName);
            textViewCode = (TextView) itemView.findViewById(R.id.textViewColorCode);
        }

        public void bindView(final Wall wall) {
            textViewName.setText(wall.getName());
            SecondaryColor color = wall.getSelectedColor();
            if (color != null) {
                textViewColor.setText(color.getName());
                textViewColor.setTextColor(color.getColor());
                textViewCode.setText(color.getColorCode());
                textViewCode.setTextColor(color.getColor());
            } else {
                textViewColor.setText("");
                textViewCode.setText("");
            }
            if (wall.isSelected()) {
                topView.setBackgroundColor(Color.BLUE);
            } else {
                topView.setBackgroundColor(Color.TRANSPARENT);
            }
            topView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onWallClickListener.onWallClick(wall);
                }
            });
        }

    }

}
