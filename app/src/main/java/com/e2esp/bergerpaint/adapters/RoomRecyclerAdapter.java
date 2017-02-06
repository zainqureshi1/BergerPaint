package com.e2esp.bergerpaint.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.e2esp.bergerpaint.R;
import com.e2esp.bergerpaint.interfaces.OnRoomClickListener;
import com.e2esp.bergerpaint.models.Room;

import java.util.ArrayList;

/**
 * Created by Zain on 1/30/2017.
 */

public class RoomRecyclerAdapter extends RecyclerView.Adapter<RoomRecyclerAdapter.RoomViewHolder> {

    private Context context;
    private ArrayList<Room> roomsList;
    private OnRoomClickListener onRoomClickListener;

    private int colorWhite;
    private int colorSelected;

    public RoomRecyclerAdapter(Context context, ArrayList<Room> roomsList, OnRoomClickListener onRoomClickListener) {
        this.context = context;
        this.roomsList = roomsList;
        this.onRoomClickListener = onRoomClickListener;

        this.colorWhite = context.getResources().getColor(R.color.white);
        this.colorSelected = context.getResources().getColor(R.color.soft_green);
    }

    @Override
    public RoomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.card_room_layout, parent, false);
        return new RoomViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return roomsList.size();
    }

    @Override
    public void onBindViewHolder(RoomViewHolder holder, int position) {
        holder.bindView(roomsList.get(position));
    }

    public class RoomViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;
        private ImageView imageView;
        private TextView textViewTitle;

        public RoomViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cardViewRoom);
            imageView = (ImageView) itemView.findViewById(R.id.imageViewRoom);
            textViewTitle = (TextView) itemView.findViewById(R.id.textViewRoomTitle);
        }

        public void bindView(final Room room) {
            imageView.setImageResource(room.getImageRes());
            textViewTitle.setText(room.getName());

            cardView.setBackgroundColor(room.isSelected() ? colorSelected : colorWhite);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onRoomClickListener.onRoomClick(room);
                }
            });
        }

    }

}
