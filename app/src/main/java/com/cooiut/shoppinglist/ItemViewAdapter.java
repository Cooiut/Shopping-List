package com.cooiut.shoppinglist;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ItemViewAdapter extends RecyclerView.Adapter {
    private ArrayList list;
    private Context context;
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    public ItemViewAdapter(Context context, ArrayList list) {
        this.list = list;
        this.context = context;
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("item");
        // TODO: 3/6/2020
    }

    public void update(ArrayList newList) {
        list.clear();
        list.addAll(newList);
        notifyDataSetChanged();
    }

    public void deleteItem(int position) {
        list.remove(position);
        notifyDataSetChanged();
    }

    public void updateQuantity(int position) {
        StoreItem s = (StoreItem) list.get(position);
        s.setQuantity(0);
        myRef.child(s.getKey()).setValue(s);
        notifyDataSetChanged();
        // TODO: 3/6/2020
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        ItemViewHolder holder = new ItemViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ItemViewHolder) holder).number.setText("" + (position + 1));
        ((ItemViewHolder) holder).item.setText(((StoreItem) list.get(position)).getItem());
        ((ItemViewHolder) holder).quantity.setText("" + ((StoreItem) list.get(position)).getQuantity());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder {
        public TextView number, item, quantity;
        public Button buttonPurchased;

        public ItemViewHolder(View view) {
            super(view);
            number = view.findViewById(R.id.textViewNumber);
            item = view.findViewById(R.id.textViewItem);
            quantity = view.findViewById(R.id.textViewQuantity);
            buttonPurchased = view.findViewById(R.id.buttonPurchased);

            buttonPurchased.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("PurchasedClicked", "Button Pressed with position ");
                    int position = getAdapterPosition();
                    updateQuantity(position);
                    buttonPurchased.setEnabled(false);
                }
            });
        }
    }
}
