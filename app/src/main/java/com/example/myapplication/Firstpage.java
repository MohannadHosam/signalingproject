package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Firstpage extends AppCompatActivity {
    Button patient;
    Button doctor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstpage);
        patient = (Button)findViewById(R.id.buttonpatient);
        doctor = (Button)findViewById(R.id.buttondr);
        patientScreen();
        doctorScreen();


    }
    private void patientScreen(){
        patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent patientIntent = new Intent(Firstpage.this, patientScreen.class);
                startActivity(patientIntent);
            }
        });
    }
    private void doctorScreen(){
        doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent doctorIntent = new Intent(Firstpage.this, doctorScreen.class);
                startActivity(doctorIntent);
            }
        });
    }
}