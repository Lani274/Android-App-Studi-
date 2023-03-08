package com.example.studiappfinished;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username,password;
    Button loginbutton,goregistrierungbutton;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ActionBar login = getSupportActionBar();
        login.setTitle("Studi Login");

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginbutton = findViewById(R.id.loginbutton);
        goregistrierungbutton = findViewById(R.id.goregistrierungbutton);
        DB = new DBHelper(this);

        loginbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if (user.equals("") || pass.equals(""))
                    Toast.makeText(MainActivity.this, "Bitte gib in allen Feldern etwas ein", Toast.LENGTH_SHORT).show();
                else {
                    Boolean testuserpass = DB.testusernamepassword(user, pass);
                    if (testuserpass == true) {
                        Toast.makeText(MainActivity.this, "Login erfolgreich", Toast.LENGTH_SHORT).show();
                        Intent intentlogin = new Intent(getApplicationContext(), FutureDeadlineActivity.class);
                        startActivity(intentlogin);
                    } else {
                        Toast.makeText(MainActivity.this, "Kein Konto, bitte registriere dich", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        goregistrierungbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoregister = new Intent(MainActivity.this, RegistrierungActivity.class);
                startActivity(gotoregister);

            }
        });
    }
}