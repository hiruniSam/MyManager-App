package com.example.mymanagerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    Button button;
    Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_goal24);

        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("   Set a Goal");

        bottomNavigationView = findViewById(R.id.bottom_navigator1);
        bottomNavigationView.setSelectedItemId(R.id.addG);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),Home.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.addG:
                        return true;

                    case R.id.viewG:
                        startActivity(new Intent(getApplicationContext(),MainView.class));
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }
        });

        button = (Button) findViewById(R.id.shortTermButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityShortAddGoal();
            }
        });

        button1 = (Button) findViewById(R.id.longTermButton);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityLongAddGoal();
            }
        });


    }


    public void openActivityShortAddGoal() {
        Intent intent = new Intent(this, ShortAddGoal.class);
        startActivity(intent);
    }

    public void openActivityLongAddGoal() {
        Intent intent1 = new Intent(this, LongAddGoal.class);
        startActivity(intent1);
    }

}