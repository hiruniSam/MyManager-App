package com.example.mymanager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;
import org.joda.time.DateTime;
import org.joda.time.Months;
import org.joda.time.MutableDateTime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddTransaction extends AppCompatActivity {
    EditText addIncome, addExpense;
    Spinner incomeCategory, expenseCategory;
    Button saveIncome, saveExpense;
    IncomeBudget incomeB;
    DatabaseReference dbRef;
    ProgressDialog loader;
    ExpenseBudget expenseB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);

        //navigation-bottomNavBar
        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.budgetNavBar);
        bottomNavigationView.setSelectedItemId(R.id.overview);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.report:
                        Intent intent = new Intent(AddTransaction.this,Report.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.backToHome:
                        Intent intent2 = new Intent(AddTransaction.this,Home.class);
                        startActivity(intent2);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.overview:
                        Intent intent3 = new Intent(AddTransaction.this,BudgetOverview.class);
                        startActivity(intent3);
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });


        addIncome = findViewById(R.id.add_amount);
        incomeCategory = findViewById(R.id.add_transaction_category);
        expenseCategory=findViewById(R.id.add_expense_category);
        saveIncome = findViewById(R.id.add_income_btn);
        saveExpense= findViewById(R.id.add_expense_btn);
        addExpense=findViewById(R.id.add_expense_amount);
        incomeB = new IncomeBudget();
        expenseB = new ExpenseBudget();
        loader = new ProgressDialog(this);



    }
    public void clearControls(){
        addIncome.setText("");
        addExpense.setText("");
    }
        public void insertIncomeData(View view){
            dbRef = FirebaseDatabase.getInstance().getReference().child("Income");

            //validations
            if(TextUtils.isEmpty(addIncome.getText().toString()))
                Toast.makeText(getApplicationContext(),"please input amount",Toast.LENGTH_SHORT).show();
            else if(TextUtils.isEmpty(incomeCategory.getSelectedItem().toString()))
                Toast.makeText(getApplicationContext(),"Please select category", Toast.LENGTH_SHORT).show();
            else{
                loader.setMessage("Adding income");
                loader.setCanceledOnTouchOutside(false);
                loader.show();
                String id = dbRef.push().getKey();
                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                Calendar cal = Calendar.getInstance();
                String date = dateFormat.format(cal.getTime());
                MutableDateTime epoch = new MutableDateTime();
                epoch.setDate(0);
                DateTime now = new DateTime();
                Months months = Months.monthsBetween(epoch,now);

                //input data
                incomeB.setCategory(incomeCategory.getSelectedItem().toString().trim());
                incomeB.setIncome(Double.parseDouble(addIncome.getText().toString().trim()));
                incomeB.setDate(date);
                incomeB.setId(id);
                incomeB.setMonth(months.getMonths());
                dbRef.child(id).setValue(incomeB).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"income added successfully",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(),task.getException().toString(),Toast.LENGTH_SHORT).show();

                        }
                        loader.dismiss();
                        clearControls();
                    }
                });


            }


        }

    public void insertExpenseData(View view){
        dbRef = FirebaseDatabase.getInstance().getReference().child("Expense");

        //validations
        if(TextUtils.isEmpty(addExpense.getText().toString()))
            Toast.makeText(getApplicationContext(),"please input amount",Toast.LENGTH_SHORT).show();
        else if(TextUtils.isEmpty(expenseCategory.getSelectedItem().toString()))
            Toast.makeText(getApplicationContext(),"Please select category", Toast.LENGTH_SHORT).show();
        else{
            loader.setMessage("Adding Expense");
            loader.setCanceledOnTouchOutside(false);
            loader.show();
            String id = dbRef.push().getKey();
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Calendar cal = Calendar.getInstance();
            String date = dateFormat.format(cal.getTime());
            MutableDateTime epoch = new MutableDateTime();
            epoch.setDate(0);
            DateTime now = new DateTime();
            Months months = Months.monthsBetween(epoch,now);

            //input data
            expenseB.setCategory(expenseCategory.getSelectedItem().toString().trim());
            expenseB.setExpense(Double.parseDouble(addExpense.getText().toString().trim()));
            expenseB.setDate(date);
            expenseB.setId(id);
            expenseB.setMonth(months.getMonths());
            dbRef.child(id).setValue(expenseB).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(getApplicationContext(),"expense added successfully",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(),task.getException().toString(),Toast.LENGTH_SHORT).show();

                    }
                    loader.dismiss();
                    clearControls();
                }
            });


        }


    }

}