package com.cooiut.shoppinglist;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    public ItemViewAdapter(Context context, ArrayList list){
        this.list = list;
        this.context = context;
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("item");
        // TODO: 3/6/2020
    }

    public void update(ArrayList newList){
        list.clear();
        list.addAll(newList);
        notifyDataSetChanged();
    }

    public void deleteItem(int position){
        list.remove(position);
        notifyDataSetChanged();
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
        ((ItemViewHolder)holder).number.setText(""+(position+1));
        ((ItemViewHolder)holder).item.setText(((StoreItem)list.get(position)).getItem());
        ((ItemViewHolder)holder).quantity.setText(""+((StoreItem)list.get(position)).getQuantity());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
