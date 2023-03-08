package com.example.studiappfinished;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import android.os.Bundle;
import java.util.ArrayList;

public class FutureDeadlineActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Button addButton;
    DBHelper myDB;
    ArrayList<String> deadline_id, deadline_titel, deadline_beschreibung;
    ImageView empty_imageview;
    TextView no_data;
    DeadlineAdapter deadlineAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_future);

        ActionBar future = getSupportActionBar();
        future.setTitle("Alle Deadlines");

        recyclerView = findViewById(R.id.recyclerView);
        empty_imageview = findViewById(R.id.empty_imageview);
        no_data = findViewById(R.id.no_data);

        addButton = (Button) findViewById(R.id.add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddDeadlineActivity.class);
                startActivity(intent);
            }
        });

        myDB = new DBHelper(FutureDeadlineActivity.this);
        deadline_id = new ArrayList<>();
        deadline_titel = new ArrayList<>();
        deadline_beschreibung = new ArrayList<>();

        storeDataInArrays();

        deadlineAdapter = new DeadlineAdapter(FutureDeadlineActivity.this,this, deadline_id, deadline_titel, deadline_beschreibung);
        recyclerView.setAdapter(deadlineAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(FutureDeadlineActivity.this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        }else{
            while (cursor.moveToNext()){
                deadline_id.add(cursor.getString(0));
                deadline_titel.add(cursor.getString(1));
                deadline_beschreibung.add(cursor.getString(2));
            }
            empty_imageview.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);

        }
    }
}