package com.example.mymanagerapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/*import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LongGoalAdapter extends RecyclerView.Adapter<LongGoalAdapter.LongGoalViewHolder>{


    Context context;

    LongGoal longGoal;

    ArrayList<LongGoal> longGoals;

    public LongGoalAdapter(Context context, ArrayList<LongGoal> longGoals) {
        this.context = context;
        this.longGoals = longGoals;
    }

    @NonNull
    @Override
    public LongGoalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_container_longgoal,parent,false);
        return new LongGoalViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull LongGoalViewHolder holder, final int position) {

        LongGoal longgoaldb = longGoals.get(position);
        holder.textGoal.setText(longgoaldb.getGoalDesL());
        holder.textDate.setText(longgoaldb.getDateL());


       /* holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final  DialogPlus dialogPlus = DialogPlus.newDialog(holder.itemView.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_long_layout))
                        .setExpanded(true,1200)
                        .create();
               // dialogPlus.show();

                View view1 =  dialogPlus.getHolderView();

                EditText goal = view1.findViewById(R.id.txtGoalL);
                EditText date = view1.findViewById(R.id.txtDateL);

                Button btnUpdate = view1.findViewById(R.id.btn_updateL);
                Button btnDelete = view1.findViewById(R.id.btn_delete);

                goal.setText(longgoaldb.getGoalDesL());
                date.setText(longgoaldb.getDateL());

                dialogPlus.show();

             /*  btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                      Map<String,Object> map = new HashMap<>();
                      map.put("goal",goal.getText().toString());
                      map.put("date",date.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("LongGoal")
                                .child(getRef().getkey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.textGoal.getContext(), "Updated Successfully", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(holder.textGoal.getContext(), "Error While Updating ", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                });



                    }
                });
            }
        });*/




    }

    @Override
    public int getItemCount() {
        return longGoals.size();
    }


    public static class LongGoalViewHolder extends RecyclerView.ViewHolder{

        TextView textGoal,textDate;


        Button btnEdit , btnDelete;

        public LongGoalViewHolder(@NonNull View itemView) {
            super(itemView);


            textGoal = itemView.findViewById(R.id.txtGoalL);
            textDate= itemView.findViewById(R.id.txtDateL);

           /* btnEdit = (Button)itemView.findViewById(R.id.btn_editL);
            btnDelete = (Button)itemView.findViewById(R.id.btn_deleteL) ;*/


        }
    }
}
