package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class patientScreen extends AppCompatActivity {
    Button login;
    Button register;
    EditText email;
    EditText password;
    FirebaseAuth Authentication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        email = (EditText) findViewById(R.id.patientemailid);
        password = (EditText) findViewById(R.id.patientpasswordid);
        login = (Button) findViewById(R.id.loginid);
        register = (Button) findViewById(R.id.registerID);


        Authentication = FirebaseAuth.getInstance();
        RegisterScreen();
        LoginScreen();
        Authentication = FirebaseAuth.getInstance();
        if (Authentication.getCurrentUser() != null) {
            startActivity(new Intent(patientScreen.this, Login.class));
        }
    }
    private void RegisterScreen() {
            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent RegisterActivityIntent = new Intent(patientScreen.this, Register.class);
                    startActivity(RegisterActivityIntent);
                }
            });
        }

    private void LoginScreen() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String email_auth = email.getText().toString().trim();
                    String pass_auth = password.getText().toString().trim();


                    Authentication.signInWithEmailAndPassword(email_auth, pass_auth).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                FirebaseUser user = Authentication.getCurrentUser();
                                Toast.makeText(patientScreen.this, "User signed in", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(patientScreen.this, Login.class));
                            } else {
                                Toast.makeText(patientScreen.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                }
                catch(Exception e){
                    Toast.makeText(patientScreen.this,"Error",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}