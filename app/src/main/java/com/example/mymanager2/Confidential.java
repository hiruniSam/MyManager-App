package com.example.mymanager2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
/*import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener; */

import java.util.ArrayList;

public class Confidential extends AppCompatActivity {

    RecyclerView secretRecyclerView;
    ArrayList<SecretDiary> secretDiaries;
    SecretAdapter secretAdapter;
    SecretDiary secretDiary;

    //DatabaseReference db;

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confidential);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_diary);
        getSupportActionBar().setTitle("  Diary");


        secretRecyclerView = findViewById(R.id.rt_secret);
       // db = FirebaseDatabase.getInstance().getReference("SecretDiary");
        secretRecyclerView.setHasFixedSize(true);

        secretRecyclerView.setLayoutManager(
                new LinearLayoutManager(this)
        );

        secretDiaries = new ArrayList<>();
        secretAdapter = new SecretAdapter(this, secretDiaries);
        secretRecyclerView.setAdapter(secretAdapter);

      /*  db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    SecretDiary secretDiary = dataSnapshot.getValue(SecretDiary.class);
                    secretDiaries.add(secretDiary);
                }
                secretAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/

        bottomNavigationView = findViewById(R.id.nav_bar);
        bottomNavigationView.setSelectedItemId(R.id.confidential);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.daily:
                        startActivity(new Intent(getApplicationContext(),Daily.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.confidential:
                        return true;

                }


                return false;
            }
        });
    }
}