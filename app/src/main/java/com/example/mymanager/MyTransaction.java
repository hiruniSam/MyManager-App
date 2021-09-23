package com.example.mymanager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MyTransaction extends AppCompatActivity {
    String post_key;
    String category;
    String amount;
    String date;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_transaction);

        Intent in = new Intent();
        post_key = in.getStringExtra("post_key");
        category = in.getStringExtra("category");
        amount = in.getStringExtra("amount");
        //date = in.getStringExtra("date");

        dbRef = FirebaseDatabase.getInstance().getReference().child("Expense");

    }

    @Override
    protected void onStart() {
        super.onStart();
        updateData();
    }


    private void updateData(){
        EditText editAmount = findViewById(R.id.edit_amount);
        EditText editCategory = findViewById(R.id.edit_transaction_category);
        EditText editDate = findViewById(R.id.edit_date);
        editAmount.setText(amount);
        editCategory.setText(category);
        //editDate.setText(date);

    }
}

