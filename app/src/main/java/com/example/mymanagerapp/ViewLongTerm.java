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

/*import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;*/

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ViewLongTerm extends AppCompatActivity {


    RecyclerView longgoalsRecyclerView;

    ArrayList<LongGoal> longgoals;
    LongGoalAdapter longGoalAdapter;


    ShortGoal longgoal;

   // DatabaseReference dbRef;

    BottomNavigationView bottomNavigationView;

   // Button button7;
    FloatingActionButton floatingActionButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_preview_24);

        setContentView(R.layout.activity_view_long_term);

        getSupportActionBar().setTitle(" Long Term Goals");





        longgoalsRecyclerView = findViewById(R.id.recyclerView);
        //dbRef = FirebaseDatabase.getInstance().getReference("LongGoal");
        longgoalsRecyclerView.setHasFixedSize(true);

        longgoalsRecyclerView.setLayoutManager(
                new LinearLayoutManager(this)
        );

        longgoals = new ArrayList<>();
        longGoalAdapter= new LongGoalAdapter(this, longgoals);
        longgoalsRecyclerView.setAdapter(longGoalAdapter);


       /* dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    LongGoal longGoal = dataSnapshot.getValue(LongGoal.class);
                    longgoals.add(longGoal);
                }
                longGoalAdapter.notifyDataSetChanged();

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

        floatingActionButton2 = (FloatingActionButton) findViewById(R.id.btnaddg2);
        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityLongAdd();
            }
        });
    }

    public void openActivityLongAdd() {
        Intent intent = new Intent(this, LongAddGoal.class);
        startActivity(intent);
    }


}