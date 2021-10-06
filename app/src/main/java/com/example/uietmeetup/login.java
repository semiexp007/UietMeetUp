package com.example.uietmeetup;

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

public class login {

    EditText emailBox,passwordBox;
    Button signinbtn,signupbtn;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        auth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailBox=findViewById(R.id.emailBox);
        passwordBox=findViewById(R.id.passwordBox);
        signinbtn=findViewById(R.id.signinbtn);
        signinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email,pass;
                pass=passwordBox.getText().toString();
                email=emailBox.getText().toString();
                auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull  Task<AuthResult> task) {

                        if(task.isSuccessful())
                        {
                            startActivity(new Intent(login.this,dashboardActivity.class));
                        }else
                        {
                            Toast.makeText(login.this,task.getException().getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        signupbtn=findViewById(R.id.createbtn);
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this,signup.class));
            }
        });
    }
}