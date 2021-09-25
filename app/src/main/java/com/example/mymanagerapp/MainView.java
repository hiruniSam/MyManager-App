package com.example.mymanagerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainView extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    Button button2;
    Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_preview_24);

        setContentView(R.layout.activity_main_view);

        getSupportActionBar().setTitle("  Goals");




        bottomNavigationView = findViewById(R.id.bottom_navigator1);
        bottomNavigationView.setSelectedItemId(R.id.viewG);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),Home.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.viewG:
                        return true;

                    case R.id.addG:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.timer:
                        startActivity(new Intent(getApplicationContext(),FocusTimer.class));
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }
        });

        button2 = (Button) findViewById(R.id.shortViewButton);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityViewShortTerm();
            }
        });

        button3 = (Button) findViewById(R.id.longViewButton);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityViewLongTerm();
            }
        });




    }

    public void openActivityViewShortTerm() {
        Intent intent = new Intent(this, ViewShortTerm.class);
        startActivity(intent);
    }

    public void openActivityViewLongTerm() {
        Intent intent1 = new Intent(this, ViewLongTerm.class);
        startActivity(intent1);
    }
}