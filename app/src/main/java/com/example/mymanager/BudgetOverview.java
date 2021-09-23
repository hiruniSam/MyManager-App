package com.example.mymanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;
import org.joda.time.DateTime;
import org.joda.time.Months;
import org.joda.time.MutableDateTime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BudgetOverview extends AppCompatActivity {

    private Object NavigationUI;
    FloatingActionButton addButton;
    private RecyclerView budgetRecycler;
    DatabaseReference dbRef;
    DatabaseReference dbRef2;
    private TextView totalEx;
    private TextView totalIn;
    TextView dateDisplay;
    private String post_key = "";
    private String expenseItem = "";
    private double amount = 0;
    private String date ="";
    ExpenseBudget expenseB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_overview);


        //navigation-bottomNavBar
        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.budgetNavBar);
        bottomNavigationView.setSelectedItemId(R.id.overview);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.report:
                        Intent intent = new Intent(BudgetOverview.this,Report.class);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.backToHome:
                        Intent intent2 = new Intent(BudgetOverview.this,Home.class);
                        startActivity(intent2);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.overview:
                        return true;
                }
                return false;
            }
        });

        budgetRecycler = findViewById(R.id.budgetRecycler);
        dbRef = FirebaseDatabase.getInstance().getReference().child("Expense");
        dbRef2 = FirebaseDatabase.getInstance().getReference().child("Income");
        totalEx = findViewById(R.id.total_expenses);
        totalIn=findViewById(R.id.total_income);

        dateDisplay=findViewById(R.id.textView8);
        expenseB = new ExpenseBudget();


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        linearLayoutManager.setReverseLayout(true);
        budgetRecycler.setHasFixedSize(true);
        budgetRecycler.setLayoutManager(linearLayoutManager);



        //display current date
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Calendar cal = Calendar.getInstance();
        String date = dateFormat.format(cal.getTime());
        dateDisplay.setText(date);

        //add total expense
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                double totalExpense = 0;
                for(DataSnapshot snap: snapshot.getChildren()){
                    ExpenseBudget expenseBudget = snap.getValue(ExpenseBudget.class);
                    totalExpense += expenseBudget.getExpense();
                    String sTotal = String.valueOf("$"+totalExpense);
                    totalEx.setText(sTotal);

                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        //add total income
        dbRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                double totalIncome = 0;
                for(DataSnapshot snap: snapshot.getChildren()){
                    IncomeBudget incomeBudget = snap.getValue(IncomeBudget.class);
                    totalIncome += incomeBudget.getIncome();
                    String sTotal = String.valueOf("$"+totalIncome);
                    totalIn.setText(sTotal);

                }

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });


        addButton = findViewById(R.id.floatingActionButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BudgetOverview.this, AddTransaction.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();




        FirebaseRecyclerOptions<ExpenseBudget> options = new FirebaseRecyclerOptions.Builder<ExpenseBudget>()
                .setQuery(dbRef,ExpenseBudget.class).build();



        FirebaseRecyclerAdapter<ExpenseBudget, MyViewHolder> adapter = new FirebaseRecyclerAdapter<ExpenseBudget, MyViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull @NotNull BudgetOverview.MyViewHolder holder, int position, @NonNull @NotNull ExpenseBudget model) {
                holder.setAmountI(model.getExpense()+"");
                holder.setDate(model.getDate());
                holder.setCategory(model.getCategory());

                switch (model.getCategory()){
                    case "Medicine":
                        holder.imageIcon.setImageResource(R.drawable.ic_baseline_account_balance_wallet_24);
                        break;
                    case "Food":
                        holder.imageIcon.setImageResource(R.drawable.food);
                        break;
                    case "Home expenses":
                        holder.imageIcon.setImageResource(R.drawable.moneybag);
                        break;
                    case "Others":
                        holder.imageIcon.setImageResource(R.drawable.target);
                        break;
                }

                holder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        post_key = getRef(position).getKey();
                        expenseItem = model.getCategory();
                        amount = model.getExpense();

                        updateData();


                    }
                });

            }

            @NonNull
            @NotNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.retrieve,parent,false);
                return new MyViewHolder(view);
            }
        };
        budgetRecycler.setAdapter(adapter);
        adapter.startListening();
        adapter.notifyDataSetChanged();


    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        View mView;
        public ImageView imageIcon;
        public TextView categoryName;


        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            mView = itemView;
            imageIcon = itemView.findViewById(R.id.imageView);
        }
        public  void setCategory(String category){
            categoryName = mView.findViewById(R.id.overview_category_name);
            categoryName.setText(category);
        }
        public void setAmountI(String amt){
            TextView amount = mView.findViewById(R.id.overview_transaction_amount);
            amount.setText(amt);
        }
        public void setDate(String dt){
            TextView date = mView.findViewById(R.id.overview_transaction_date);
            date.setText(dt);
        }

    }
    private void updateData(){
        AlertDialog.Builder myDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = LayoutInflater.from(this);
        View mView = inflater.inflate(R.layout.update,null);

        myDialog.setView(mView);
        final AlertDialog dialog = myDialog.create();

        final EditText mItem = mView.findViewById(R.id.edit_transaction_category);
        final EditText mAmount = mView.findViewById(R.id.edit_amount);

        mItem.setText(expenseItem);
        mAmount.setText(String.valueOf(amount));
        mAmount.setSelection(String.valueOf(amount).length());

        Button update = mView.findViewById(R.id.edit_income_btn2);
        Button delete = mView.findViewById(R.id.edit_income_btn);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               amount = Double.parseDouble(mAmount.getText().toString());

                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                Calendar cal = Calendar.getInstance();
                String date = dateFormat.format(cal.getTime());

                MutableDateTime epoch = new MutableDateTime();
                epoch.setDate(0);
                DateTime now = new DateTime();
                Months months = Months.monthsBetween(epoch, now);

                expenseB.setCategory(expenseItem);
                expenseB.setExpense(amount);
                expenseB.setDate(date);
                expenseB.setId(post_key);
                expenseB.setMonth(months.getMonths());
                dbRef.child(post_key).setValue(expenseB).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Updated Successfully",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(),task.getException().toString(),Toast.LENGTH_SHORT).show();

                        }

                    }
                });
                dialog.dismiss();

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbRef.child(post_key).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Deleted Successfully",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(),task.getException().toString(),Toast.LENGTH_SHORT).show();

                        }

                    }
                });
                dialog.dismiss();
            }
        });

        dialog.show();

    }

}