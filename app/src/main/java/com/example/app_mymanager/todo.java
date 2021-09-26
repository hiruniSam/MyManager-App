package com.example.app_mymanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//import com.firebase.ui.database.FirebaseRecyclerAdapter;
//import com.firebase.ui.database.FirebaseRecyclerOptions;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;

import java.util.Locale;

public class todo extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    private RecyclerView recyclerView;
    private Button addTask;


   // private DatabaseReference reference;


    private ProgressDialog loader;

    private String key = "";
    private String category;
    private String task;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_todo);
        getSupportActionBar().setTitle("    To-Do List");



        bottomNavigationView = findViewById(R.id.bottom_navigator);
        bottomNavigationView.setSelectedItemId(R.id.todo);

        recyclerView = findViewById(R.id.todoRecyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        loader = new ProgressDialog(this);

        //reference = FirebaseDatabase.getInstance().getReference().child("tasks");

        addTask = findViewById(R.id.addTodo);

        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTodo();
            }
        });









        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;


                    case R.id.calender:
                        startActivity(new Intent(getApplicationContext(),event.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.todo:

                        return true;
                }
                return false;
            }
        });
    }

    //add tasks into list
    private void addTodo() {

        AlertDialog.Builder myDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = LayoutInflater.from(this);
        View myView = inflater.inflate(R.layout.add_task, null);
        myDialog.setView(myView);

        final AlertDialog dialog = myDialog.create();
        dialog.setCancelable(false);


        final EditText category = myView.findViewById(R.id.addCategory);
        final EditText task = myView.findViewById(R.id.addTask);
        Button save = myView.findViewById(R.id.btn_save);
        Button cancel = myView.findViewById(R.id.btn_cancel);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        save.setOnClickListener((v)->{

            String mCategory = category.getText().toString().trim();
            String mTask = task.getText().toString().trim();
           // String id = reference.push().getKey();

            if(TextUtils.isEmpty(mCategory)){
                category.setError("Category is required");
                return;
            }

            if(TextUtils.isEmpty(mTask)){
                task.setError("Task is required");
                return;
            }else{

                loader.setMessage("Adding your data");
                loader.setCanceledOnTouchOutside(false);
                loader.show();

              //  Model model = new Model(mCategory,mTask,id);

              //  reference.child(id).setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
                  //  @Override
                   // public void onComplete(@NonNull Task<Void> task) {
                       // if(task.isSuccessful()){
                            Toast.makeText(todo.this, "Task has been added successfully", Toast.LENGTH_SHORT).show();
                            loader.dismiss();
                       // }else{
                          //  String error = task.getException().toString();
                          //  Toast.makeText(todo.this, "Failed: + "+error, Toast.LENGTH_SHORT).show();
                            loader.dismiss();
                        }
                  //  }
               // });




          //  }
                dialog.dismiss();
        });




        dialog.show();

    }

   /* @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Model> options = new FirebaseRecyclerOptions.Builder<Model>()
                .setQuery(reference,Model.class)
                .build();

        FirebaseRecyclerAdapter<Model,MyviewHolder> adapter= new FirebaseRecyclerAdapter<Model, MyviewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyviewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull Model model) {

                holder.setCategory(model.getCategory());
                holder.setTask(model.getTask());


                holder.mView.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {

                       // key = getRef(position).getKey();
                        category = model.getCategory();
                        task = model.getTask();

                       updateTask();


                    }
                });



            }

            @NonNull
            @Override
            public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.retrived_layout,parent,false);
                return new MyviewHolder(view);
            }
        };

        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }

    public static class MyviewHolder extends RecyclerView.ViewHolder{

        View mView;

        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            mView =itemView;
        }

        public void setCategory(String category){
            TextView categoryTextView = mView.findViewById(R.id.tv_category);
            categoryTextView.setText(category);
        }

        public void setTask(String task){
            TextView taskTextView = mView.findViewById(R.id.tv_task);
            taskTextView.setText(task);
        }
    }


    private void updateTask(){
       AlertDialog.Builder myDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.update_todo,null);
        myDialog.setView(view);

        AlertDialog dialog = myDialog.create();

        EditText mCategory = view.findViewById(R.id.et_updateCategory);
        EditText mTask = view.findViewById(R.id.et_updateTask);

        mCategory.setText(category);
        mCategory.setSelection(category.length());

        mTask.setText(task);
       mTask.setSelection(task.length());

        Button upButton = view.findViewById(R.id.btn_update);
       Button delButton =view.findViewById(R.id.btn_delete);

        upButton.setOnClickListener(new View.OnClickListener(){

           @Override
            public void onClick(View v) {

                category = mCategory.getText().toString().trim();
                task = mTask.getText().toString().trim();


                Model model = new Model(category,task,key);
                reference.child(key).setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
                   @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if(task.isSuccessful()){
                            Toast.makeText(todo.this, "Data updated Successfully", Toast.LENGTH_SHORT).show();
                        }else{
                           String err = task.getException().toString();
                            Toast.makeText(todo.this, "Failed "+err, Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                dialog.dismiss();

           }
        });

        delButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                 reference.child(key).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                     public void onComplete(@NonNull Task<Void> task) {

                         if(task.isSuccessful()){
                             Toast.makeText(todo.this, "Task Deleted Successfully", Toast.LENGTH_SHORT).show();
                         }else{
                             String err =task.getException().toString();
                             Toast.makeText(todo.this, "Failed "+err, Toast.LENGTH_SHORT).show();
                         }

                     }
                 });
                dialog.dismiss();
            }
        });


        dialog.show();
    }
*/


}