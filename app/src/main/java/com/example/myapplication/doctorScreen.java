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

public class doctorScreen extends AppCompatActivity {
    Button login;
    Button register;
    EditText email;
    EditText password;
    FirebaseAuth Authentication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctorscreen);

        email = (EditText) findViewById(R.id.emaildr);
        password = (EditText) findViewById(R.id.passworddr);
        login = (Button) findViewById(R.id.loginiddr);
        register = (Button) findViewById(R.id.registeriddr);


        Authentication = FirebaseAuth.getInstance();
        RegisterScreen();
        LoginScreen();
        Authentication = FirebaseAuth.getInstance();
        if (Authentication.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), Login.class));
            finish();
        }
    }
    private void RegisterScreen() {
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent RegisterActivityIntent = new Intent(doctorScreen.this, Register.class);
                startActivity(RegisterActivityIntent);
            }
        });
    }

    private void LoginScreen() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email_auth = email.getText().toString().trim();
                String pass_auth = password.getText().toString().trim();

                String email_JSON = email.getText().toString();



                Authentication.signInWithEmailAndPassword(email_auth, pass_auth).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = Authentication.getCurrentUser();
                            Toast.makeText(doctorScreen.this, "User signed in", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(doctorScreen.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                Intent loginActivityIntent = new Intent(doctorScreen.this, Login.class);
                startActivity(loginActivityIntent);
            }
        });
    }
}