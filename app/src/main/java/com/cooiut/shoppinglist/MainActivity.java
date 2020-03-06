package com.cooiut.shoppinglist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<StoreItem> items;
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("item");

        items = new ArrayList<StoreItem>();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewList);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                items.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    StoreItem s = ds.getValue(StoreItem.class);
                    items.add(s);
                }
                adapter = new ItemViewAdapter(MainActivity.this, items);
                recyclerView.setAdapter(adapter);
                ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDelete((ItemViewAdapter) adapter));
                itemTouchHelper.attachToRecyclerView(recyclerView);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
    }

    public void add(View view) {
        EditText item = (EditText) findViewById(R.id.editTextItem);
        EditText quantity = (EditText) findViewById(R.id.editTextQuantity);
        String i = item.getText().toString();
        double q = Double.parseDouble((quantity.getText().toString()));

        String key = myRef.push().getKey();

        StoreItem s = new StoreItem(key, i, q);
        items.add(s);
        myRef.child(key).setValue(s);

        ((ItemViewAdapter) adapter).update(items);
        item.setText("");
        quantity.setText("");

    }
}
