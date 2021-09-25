package com.example.mymanagerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.MenuItem;
import android.view.View;
import android.widget.Chronometer;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class FocusTimer extends AppCompatActivity {

    private Chronometer chronometer;
    private long pauseOffset;
    private boolean running;

     //navigationBar

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_timer_24);

        setContentView(R.layout.activity_focus_timer);

        getSupportActionBar().setTitle("   Focus Timer ");


        chronometer = findViewById(R.id.chronometer);



        bottomNavigationView = findViewById(R.id.bottom_navigator1);
        bottomNavigationView.setSelectedItemId(R.id.timer);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),Home.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.timer:
                        return true;
                    case R.id.addG:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);



                }
                return false;
            }
        });
    }


    public void startChronometer(View v ){

        if(!running){

            chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            chronometer.start();
            running=true;
        }

    }
    public void pauseChronometer(View v ){

        if(running){
            chronometer.stop();
            pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
            running = false;
        }

    }
    public void resetChronometer(View v ){
        chronometer.setBase(SystemClock.elapsedRealtime());
        pauseOffset=0;

    }
}