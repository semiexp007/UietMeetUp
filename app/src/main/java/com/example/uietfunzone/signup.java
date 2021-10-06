package com.example.uietfunzone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class signup extends AppCompatActivity {
    FirebaseAuth auth;

    FirebaseFirestore database;
    EditText emailBox,passwordBox,namebox;
    Button signinbtn,signupbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        database=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        emailBox=findViewById(R.id.emailBox);
        passwordBox=findViewById(R.id.passwordBox);
        signinbtn=findViewById(R.id.signinbtn);
        signupbtn=findViewById(R.id.createbtn);
        namebox=findViewById(R.id.editTextTextPersonName);
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String email,name,passward;
               email=emailBox.getText().toString();
               passward=passwordBox.getText().toString();
               name=namebox.getText().toString();
               user u=new user();
               u.setEmail(email);
               u.setName(name);
               u.setPass(passward);


               auth.createUserWithEmailAndPassword(email,passward).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                       if(task.isSuccessful()){
                           database.collection("users").document().set(u).addOnSuccessListener(new OnSuccessListener<Void>() {
                               @Override
                               public void onSuccess(Void unused) {
                                  startActivity(new Intent(signup.this,login.class));
                               }
                           });

                           Toast.makeText(signup.this,"Successfully registered ",Toast.LENGTH_SHORT).show();
                       }else
                       {
                           Toast.makeText(signup.this,task.getException().getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                       }

                   }
               });
            }
        });
    }
}


