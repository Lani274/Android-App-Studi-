package com.example.studiappfinished;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;

public class RegistrierungActivity extends AppCompatActivity {

    EditText username1,password1, repassword;
    Button registrierungsbutton, schonregistriertbutton;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrierung);

        ActionBar regi = getSupportActionBar();
        regi.setTitle("Registrierung");

        username1 = (EditText) findViewById(R.id.username1);
        password1 = (EditText) findViewById(R.id.password1);
        repassword = (EditText) findViewById(R.id.repassword);
        registrierungsbutton = (Button) findViewById(R.id.registrierungsbutton);
        schonregistriertbutton = (Button) findViewById(R.id.schonregistriertbutton);
        DB = new DBHelper(this);

        registrierungsbutton.setOnClickListener(new View.OnClickListener(){
         @Override
         public void onClick(View view) {
             String user= username1.getText().toString();
             String pass= password1.getText().toString();
             String repass=repassword.getText().toString();

             if (user.equals("") ||pass.equals("")||repass.equals(""))
                 Toast.makeText(RegistrierungActivity.this, "Bitte gib in allen Feldern etwas ein", Toast.LENGTH_SHORT).show();
             else{
                 if(pass.equals(repass)){
                     Boolean testuser = DB.testusername(user);
                     if(testuser==false){
                         Boolean einfuegen=DB.einfuegenData(user,pass);
                         if(einfuegen==true){
                             Toast.makeText(RegistrierungActivity.this, "Registrierung erfolgreich", Toast.LENGTH_SHORT).show();
                             Intent intent = new Intent(RegistrierungActivity.this,MainActivity.class);
                             startActivity(intent);
                         }else{
                             Toast.makeText(RegistrierungActivity.this, "Registrierung fehlgeschlagen", Toast.LENGTH_SHORT).show();
                         }
                         }else{
                             Toast.makeText(RegistrierungActivity.this, "Konto existiert bereits! Bitte einloggen", Toast.LENGTH_SHORT).show();
                         }

         }
        }
    }
});

        schonregistriertbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent gotoLogin = new Intent(RegistrierungActivity.this, MainActivity.class);
                    startActivity(gotoLogin);
                }
            });
    }
}
