package com.example.studiappfinished;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.os.Bundle;
import java.util.ArrayList;


public class DeadlineAdapter extends RecyclerView.Adapter <DeadlineAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList deadline_id, deadline_titel, deadline_beschreibung;


    DeadlineAdapter(Activity activity, Context context, ArrayList deadline_id, ArrayList deadline_titel, ArrayList deadline_beschreibung){
        this.activity = activity;
        this.context = context;
        this.deadline_id = deadline_id;
        this.deadline_titel = deadline_titel;
        this.deadline_beschreibung = deadline_beschreibung;
    }

    @NonNull
    @Override
    public DeadlineAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.deadline_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.deadline_id_txt.setText(String.valueOf(deadline_id.get(position)));
        holder.deadline_titel_txt.setText(String.valueOf(deadline_titel.get(position)));
        holder.deadline_beschreibung_txt.setText(String.valueOf(deadline_beschreibung.get(position)));

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateDeadlineActivity.class);
                intent.putExtra("id", String.valueOf(holder.deadline_id_txt.getText().toString()));
                intent.putExtra("titel", String.valueOf(deadline_titel.get(position)));
                intent.putExtra("beschreibung", String.valueOf(deadline_beschreibung.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return deadline_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView deadline_id_txt, deadline_titel_txt, deadline_beschreibung_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            deadline_id_txt = itemView.findViewById(R.id.deadline_id_txt);
            deadline_titel_txt = itemView.findViewById(R.id.deadline_titel_txt);
            deadline_beschreibung_txt = itemView.findViewById(R.id.deadline_Beschreibung_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
}}

