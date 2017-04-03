package com.e2esp.bergerpaint.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.e2esp.bergerpaint.R;
import com.e2esp.bergerpaint.interfaces.OnProductClickListener;
import com.e2esp.bergerpaint.models.Product;

import java.util.ArrayList;

/**
 * Created by Zain on 2/1/2017.
 */

public class ProductRecyclerAdapter extends RecyclerView.Adapter<ProductRecyclerAdapter.ProductViewHolder> {

    private Context context;
    private ArrayList<Product> productsList;
    private OnProductClickListener onProductClickListener;

    private int selectedColor;

    public ProductRecyclerAdapter(Context context, ArrayList<Product> productsList, int selectedColor, OnProductClickListener onProductClickListener) {
        this.context = context;
        this.productsList = productsList;
        this.selectedColor = selectedColor;
        this.onProductClickListener = onProductClickListener;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.card_product_layout, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        holder.bindView(productsList.get(position));
    }

    public void setColor(int color) {
        this.selectedColor = color;
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textViewTitle;
        private TextView textViewDescription;
        private TextView textViewLearnMore;

        public ProductViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageViewProduct);
            textViewTitle = (TextView) itemView.findViewById(R.id.textViewProductTitle);
            textViewDescription = (TextView) itemView.findViewById(R.id.textViewProductDescription);
            textViewLearnMore = (TextView) itemView.findViewById(R.id.textViewProductLearnMore);
        }

        public void bindView(final Product product) {
            imageView.setImageResource(product.getImageRes());
            textViewTitle.setText(product.getName());
            textViewDescription.setText(product.getDescription());

            textViewLearnMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onProductClickListener.onLearnMoreClick(product);
                }
            });
        }

    }

}
