package com.example.mymanagerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;


import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

/*import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;*/


import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Calendar;

public class ShortAddGoal extends AppCompatActivity {

    EditText mDateFormat,txtGoal;
    Button btnEnter;

    ShortGoal obShort;

   // DatabaseReference dbRef;

    //method to clear input data

    private void clearControls(){
        txtGoal.setText("");
        mDateFormat.setText("");
    }



    //Date picker dialog
    DatePickerDialog.OnDateSetListener onDateSetListener;

    BottomNavigationView bottomNavigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_addwhite24);

        setContentView(R.layout.activity_short_add_goal);

        getSupportActionBar().setTitle("  Add Short Term Goals");

        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day= calendar.get(Calendar.DAY_OF_MONTH);



        txtGoal = findViewById(R.id.txt_shortgoal);
        mDateFormat = findViewById(R.id.txt_shortdate);

        btnEnter = findViewById(R.id.short_add_button);

        obShort = new ShortGoal();



       mDateFormat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        ShortAddGoal.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        onDateSetListener,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();


            }
        });

        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month =month+1;

                String date = day+"/"+month+"/"+year;
                mDateFormat.setText(date);

            }
        };




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
                        startActivity(new Intent(getApplicationContext(),ViewShortTerm.class));
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }
        });





    }

    public void Insert(View view){

      //  dbRef = FirebaseDatabase.getInstance().getReference().child("ShortGoal");

        try{

            if(TextUtils.isEmpty(txtGoal.getText().toString().trim())){
                Toast.makeText(getApplicationContext(),"Please Enter the Goal",Toast.LENGTH_LONG).show();
            }else if(TextUtils.isEmpty(mDateFormat.getText().toString().trim())){
                 Toast.makeText(getApplicationContext(),"Please Enter the date",Toast.LENGTH_LONG).show();
            }else{
                obShort.setGoalDes(txtGoal.getText().toString().trim());
                obShort.setDateS(mDateFormat.getText().toString().trim());

              //cmt  dbRef.push().setValue(obShort);

               //new cmt Toast.makeText(getApplicationContext(),"Successful Insertion",Toast.LENGTH_LONG).show();
                clearControls();
            }

        }catch(NumberFormatException e){
           Toast.makeText(getApplicationContext(),"Number Format Exception",Toast.LENGTH_LONG).show();
        }
    }





}