package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    Button google_maps;
    Button extraData_button;
    Button sign_out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        google_maps = (Button)findViewById(R.id.googlemapsbutton);
        extraData_button = (Button)findViewById(R.id.extradatabutton);
        sign_out = (Button)findViewById(R.id.buttonsignout);

        data();
        sign_out();
        Google_Maps();
    }
    private void sign_out(){
        sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent return_first = new Intent(Login.this,Firstpage.class);
                startActivity(return_first);
                Toast.makeText(Login.this,"Signed out",Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void data(){
        extraData_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dataIntent = new Intent(Login.this, ExtraDataScreen.class);
                startActivity(dataIntent);
            }
        });
    }
    private void Google_Maps(){
        google_maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,Map.class));
            }
        });
    }
}