package com.example.kosemcafe3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class kayitol extends AppCompatActivity {
    private EditText txtUsernameRegister;
    private EditText txtEmailRegister;
    private EditText txtPasswordRegister;
    private Button buttonRegister;
    private Button buttonLogin;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayitol);
        buttonRegister=(Button)findViewById(R.id.buttonRegister);
        buttonLogin=(Button)findViewById(R.id.buttonLogin);
        txtEmailRegister=(EditText) findViewById(R.id.txtEmailRegister);
        txtUsernameRegister=(EditText) findViewById(R.id.txtUsernameRegister);
        txtPasswordRegister=(EditText) findViewById(R.id.txtPasswordRegister);
        mAuth=FirebaseAuth.getInstance();
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ad=txtUsernameRegister.getText().toString();
                String email=txtEmailRegister.getText().toString();
                String sifre=txtPasswordRegister.getText().toString();
                register_user(ad,email,sifre);
            }
        });
    }

    private void register_user(String ad, String email, String sifre) {
        mAuth.createUserWithEmailAndPassword(email,sifre).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Intent intent=new Intent(kayitol.this,MainActivity.class);
                    startActivity(intent);
                }
                else{

                }
            }
        });

    }
}
