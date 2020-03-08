package com.cooiut.shoppinglist;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SwipeToDelete extends ItemTouchHelper.SimpleCallback {
    private ItemViewAdapter adapter;
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    public SwipeToDelete(ItemViewAdapter adapter){
        super(0, ItemTouchHelper.LEFT);
        this.adapter = adapter;
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("item");
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        int position = viewHolder.getAdapterPosition();
        adapter.deleteItem(position);
    }
}
