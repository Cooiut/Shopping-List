package com.cooiut.shoppinglist;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ItemViewHolder extends RecyclerView.ViewHolder {
    public TextView number, item, quantity;

    public ItemViewHolder(View view) {
        super(view);
        number = (TextView) view.findViewById(R.id.textViewNumber);
        item = (TextView) view.findViewById(R.id.textViewItem);
        quantity = (TextView) view.findViewById(R.id.textViewQuantity);
    }
}
