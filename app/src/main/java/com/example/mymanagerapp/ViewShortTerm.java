package com.example.mymanagerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
/*import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;*/

import java.util.ArrayList;


public class ViewShortTerm extends AppCompatActivity {

    RecyclerView shortgoalsRecyclerView;

    ArrayList<ShortGoal> shortgoals;
    GoalAdapter goalAdapter;


    ShortGoal shortgoal;

    //DatabaseReference dbRef;




    BottomNavigationView bottomNavigationView;

    //Button button8;
    FloatingActionButton floatingActionButton;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_preview_24);

        setContentView(R.layout.activity_view_short_term);

        getSupportActionBar().setTitle(" Short Term Goals");




        shortgoalsRecyclerView = findViewById(R.id.recyclerView);
       // dbRef = FirebaseDatabase.getInstance().getReference("ShortGoal");
        shortgoalsRecyclerView.setHasFixedSize(true);

        shortgoalsRecyclerView.setLayoutManager(
                new LinearLayoutManager(this)
        );

        shortgoals = new ArrayList<>();
        goalAdapter = new GoalAdapter(this, shortgoals);
        shortgoalsRecyclerView.setAdapter(goalAdapter);


       /* dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ShortGoal shortGoal = dataSnapshot.getValue(ShortGoal.class);
                    shortgoals.add(shortGoal);
                }


                goalAdapter.notifyDataSetChanged();

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/




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

                }
                return false;
            }
        });


        floatingActionButton = (FloatingActionButton) findViewById(R.id.btnaddg);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityShortAdd();
            }
        });
    }




    public void openActivityShortAdd() {
        Intent intent = new Intent(this, ShortAddGoal.class);
        startActivity(intent);
    }
}