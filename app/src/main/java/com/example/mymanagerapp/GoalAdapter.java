package com.example.mymanagerapp;

import static android.media.CamcorderProfile.get;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.ViewGroup;
import android.view.View;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//new comments
/*import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;

public class GoalAdapter extends RecyclerView.Adapter<GoalAdapter.GoalViewHolder> {

    private  String goal;
    private  String date;

    Context context;
   //new DatabaseReference dbRef;

    ShortGoal obShort;
    ArrayList<ShortGoal> shortGoals;



    public GoalAdapter(Context context, ArrayList<ShortGoal> shortGoals) {
        this.context = context;
        this.shortGoals = shortGoals;
    }

    @NonNull
    @Override
    public GoalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.item_container_shortgoal,parent,false);
        return new GoalViewHolder(v);
    }



    @Override
    public void onBindViewHolder(@NonNull GoalViewHolder holder, final int position ) {



        ShortGoal shortgoaldb = shortGoals.get(position);
        holder.textGoal.setText(shortgoaldb.getGoalDes());
        holder.textDate.setText(shortgoaldb.getDateS());



      /*  holder.btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  final DialogPlus dialogPlus = DialogPlus.newDialog(holder.textGoal.getContext()).setContentHolder(new ViewHolder(R.layout.update_layout))
                          .setExpanded(true,1200).create();



                View v1 =  dialogPlus.getHolderView();

                EditText goal = v1.findViewById(R.id.txtGoal);
                EditText date = v1.findViewById(R.id.txtDate);

                Button btnUpdate = v1.findViewById(R.id.btn_update);
                Button btnDelete = v1.findViewById(R.id.btn_delete);

                goal.setText(shortgoaldb.getGoalDes());
                date.setText(shortgoaldb.getDateS());

                dialogPlus.show();


                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Map<String,Object> map = new HashMap<>();

                        map.put("goalDes",goal.getText().toString());
                        map.put("dateS",date.getText().toString());

                     FirebaseDatabase.getInstance().getReference().child("ShortGoal")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.textGoal.getContext(), "Updated Successfully", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(Exception e) {
                                        Toast.makeText(holder.textGoal.getContext(), "Error while Updating", Toast.LENGTH_SHORT).show();
                                         dialogPlus.dismiss();
                                    }
                                });
                    }
                });

            }


        });*/

        /*holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.textGoal.getContext());
                builder.setTitle(" Are You Sure Want To delete The Goal ?");
                builder.setMessage("Deleted Data can't be Undo. ");


                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {*/

                       /* FirebaseDatabase.getInstance().getReference().child("ShortGoal")
                                .child(getRef().getKey()).removeValue();*/
                        /*dbRef = FirebaseDatabase.getInstance().getReference().child("ShortGoal")
                                .child("obShort");
                        dbRef.removeValue();*/

                   /* }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Toast.makeText(holder.textGoal.getContext(), " Cancelled ", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.show();
            }


        });*/







    }



    @Override
    public int getItemCount() {
        return shortGoals.size();
    }



    public static class GoalViewHolder extends RecyclerView.ViewHolder{

        TextView textGoal,textDate;

        Button btn_edit;
        Button btn_delete;

        public GoalViewHolder(@NonNull View itemView) {
            super(itemView);

            textGoal = itemView.findViewById(R.id.txtGoal);
            textDate= itemView.findViewById(R.id.txtDate);

           /* btn_edit = (Button) itemView.findViewById(R.id.btn_edit);
            btn_delete = (Button) itemView.findViewById(R.id.btn_delete);*/

        }
    }


}












