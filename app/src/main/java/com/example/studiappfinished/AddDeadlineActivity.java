package com.example.studiappfinished;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddDeadlineActivity extends AppCompatActivity {

    EditText titel;
    EditText beschreibung;
    Button addbutton;
    Button gofuturebutton;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_deadline);

        ActionBar future = getSupportActionBar();
        future.setTitle("Deadline hinzufügen");

        DB = new DBHelper(this);
        titel = findViewById(R.id.titel);
        beschreibung = findViewById(R.id.beschreibung);
        addbutton = findViewById(R.id.addbutton);


        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DB.addDeadline(titel.getText().toString(), beschreibung.getText().toString());
            }
        });

        gofuturebutton=(Button) findViewById(R.id.gofuturebutton);
        gofuturebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FutureDeadlineActivity.class);
                startActivity(intent);
            }
        });
    }}