package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    Button register;
    EditText email;
    EditText password;
    FirebaseAuth Authentication;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        email = (EditText)findViewById(R.id.emailid2);
        password = (EditText)findViewById(R.id.passwordid2);
        register = (Button)findViewById(R.id.registerid2);


        Authentication = FirebaseAuth.getInstance();
       if(Authentication.getCurrentUser()!=null){
           finish();
//            startActivity(new Intent(patient.this, patientScreen.class));
        }
        addOnClickListener();
    }
    void addOnClickListener(){
        register.setOnClickListener(new View.OnClickListener(){
           public void onClick(View v){
              String email_auth = email.getText().toString().trim();
              String pass_auth = password.getText().toString().trim();

              if(TextUtils.isEmpty(email_auth)){
                  email.setError("Email is required");
                  return;
              }
               if(TextUtils.isEmpty(pass_auth)){
                   password.setError("Password is required");
                   return;
               }
               Authentication.createUserWithEmailAndPassword(email_auth,pass_auth).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                       if(task.isSuccessful()){
                           Toast.makeText(Register.this, "User created", Toast.LENGTH_SHORT).show();
                           finish();
                       }
                       else{
                           Toast.makeText(Register.this, "Error!"+ task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                       }
                   }
               });
            }
        });
    }
}