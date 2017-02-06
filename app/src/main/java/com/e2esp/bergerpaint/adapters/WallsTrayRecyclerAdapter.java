package com.e2esp.bergerpaint.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.e2esp.bergerpaint.R;
import com.e2esp.bergerpaint.interfaces.OnTraysWallClickListener;
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

        private LinearLayout linearLayoutWall;
        private TextView textViewName;

        public WallViewHolder(View itemView) {
            super(itemView);
            linearLayoutWall = (LinearLayout) itemView.findViewById(R.id.linearLayoutWall);
            textViewName = (TextView) itemView.findViewById(R.id.textViewWallName);
        }

        public void bindView(final Wall wall) {
            textViewName.setText(wall.getName());
            linearLayoutWall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onWallClickListener.onWallClick(wall);
                }
            });
        }

    }

}
