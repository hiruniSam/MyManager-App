package com.example.mymanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Report extends AppCompatActivity {

    float expenses[] = {100f, 200f,300,400f};
    String categories[] = {"food", "home", "medicines", "others"};



    DatabaseReference dbRef;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        //navigation-bottomNavBar
        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.budgetNavBar);
        bottomNavigationView.setSelectedItemId(R.id.report);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.overview:
                        Intent intent = new Intent(Report.this,BudgetOverview.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.backToHome:
                        Intent intent2 = new Intent(Report.this,Home.class);
                        startActivity(intent2);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.report:
                        return true;
                }
                return false;
            }
        });


        setupPieChart();
    }

    private void setupPieChart() {
//        populating a list of pie entries

        List<PieEntry> pieEntries = new ArrayList<>();
        for (int i=0; i< expenses.length; i++){
            pieEntries.add(new PieEntry(expenses[i], categories[i]));
        }

        PieDataSet dataSet = new PieDataSet(pieEntries, "Monthly Expenses");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData data = new PieData(dataSet);

//        get chart

        PieChart chart = (PieChart) findViewById(R.id.chart);
        chart.setData(data);
        chart.animateY(2000);
        chart.invalidate();
    }


}

