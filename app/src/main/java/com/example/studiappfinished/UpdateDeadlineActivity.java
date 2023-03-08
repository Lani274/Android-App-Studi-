package com.example.studiappfinished;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateDeadlineActivity extends AppCompatActivity {

    EditText titel_eingabe, beschreibung_eingabe;
    Button update_button, delete_button;
    String id, titel, beschreibung;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_deadline);

        titel_eingabe = findViewById(R.id.titel2);
        beschreibung_eingabe = findViewById(R.id.beschreibung2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        getAndSetIntentData();

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(titel);
    }
        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBHelper myDB = new DBHelper(UpdateDeadlineActivity.this);
                titel = titel_eingabe.getText().toString().trim();
                beschreibung = beschreibung_eingabe.getText().toString().trim();

                myDB.updateData(id, titel, beschreibung);
            }
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });
}

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("titel") &&
                getIntent().hasExtra("beschreibung")){
            id = getIntent().getStringExtra("id");
            titel = getIntent().getStringExtra("titel");
            beschreibung = getIntent().getStringExtra("beschreibung");

            titel_eingabe.setText(titel);
            beschreibung_eingabe.setText(beschreibung);

        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }
    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(titel + " Löschen ?");
        builder.setMessage("Möchtest du " + titel + " wirklich löschen ?");
        builder.setPositiveButton("Ja", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DBHelper myDB = new DBHelper(UpdateDeadlineActivity.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("Nein", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        builder.create().show();
    }
}