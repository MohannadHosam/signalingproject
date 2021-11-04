package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.HashMap;

public class ExtraDataScreen extends AppCompatActivity {
    EditText user_name;
    EditText user_age;
    Button create;
    Button Finish_button;
    DatabaseReference UserRef;
    Button Update;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra_data);

        user_name = (EditText) findViewById(R.id.userName);
        user_age = (EditText) findViewById(R.id.userAge);
        create = (Button) findViewById(R.id.createButton);
        Finish_button = (Button) findViewById(R.id.FinishButton);
        UserRef = FirebaseDatabase.getInstance().getReference();
        Update = (Button)findViewById(R.id.updatebutton);
        creating();
        updating();
        Finish();
    }

        private void creating () {
            create.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int age = Integer.parseInt(user_age.getText().toString());
                    String name = user_name.getText().toString();
                    user User = new user(name, age);
                    String key = firebaseAuth.getInstance().getCurrentUser().getUid();
                    HashMap hashMap = new HashMap();
                    hashMap.put("age", age);
                    hashMap.put("name", name);
                    UserRef.child(key).setValue(User);
                    Toast.makeText(ExtraDataScreen.this, "Data is added", Toast.LENGTH_SHORT).show();
                }
            });
    }
    private void updating () {
        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int age = Integer.parseInt(user_age.getText().toString());
                String name = user_name.getText().toString();
                HashMap hashMap = new HashMap();
                hashMap.put("name",name);
                hashMap.put("age",age);
                String key = firebaseAuth.getInstance().getCurrentUser().getUid();
                UserRef.child(key).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {
                        Toast.makeText(ExtraDataScreen.this, "Updated", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
        private void Finish(){
            Finish_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }