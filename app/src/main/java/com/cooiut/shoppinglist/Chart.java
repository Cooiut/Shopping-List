package com.cooiut.shoppinglist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;

import android.os.Bundle;
import android.provider.ContactsContract;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Chart extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private ArrayList<StoreItem> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        Bundle extras = getIntent().getExtras();
        items = extras.getParcelableArrayList("arraylist");

        PieChart chart = findViewById(R.id.chart);

        double sum = 0;
        for (StoreItem s : items) {
            sum += s.getQuantity();
        }

        List<PieEntry> entries = new ArrayList<PieEntry>();
        for (StoreItem s : items) {
            entries.add(new PieEntry((float) (s.getQuantity()/sum), s.getItem()));
        }
        PieDataSet set = new PieDataSet(entries, Double.toString(sum));
        set.setColors(ColorTemplate.VORDIPLOM_COLORS);
        PieData data = new PieData(set);
        chart.setData(data);
        chart.invalidate();
    }
}