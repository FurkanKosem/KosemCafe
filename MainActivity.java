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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private Button signup;
    private Button loginButton;
    private EditText password;
    private FirebaseAuth mAuth;
    private EditText email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginButton=(Button)findViewById(R.id.loginButton);
        signup=(Button)findViewById(R.id.signup);
        password=(EditText)findViewById(R.id.password);
        email=(EditText)findViewById(R.id.email);
        mAuth=FirebaseAuth.getInstance();
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(MainActivity.this,kayitol.class);
                startActivity(intent);
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail=email.getText().toString();
                String sifre=password.getText().toString();
                giris(mail,sifre);
            }
        });
    }

    private void giris(String mail, String sifre) {
        mAuth.signInWithEmailAndPassword(mail, sifre).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
              if ( task.isSuccessful()){
                  final String id =mAuth.getCurrentUser().getUid();
                  FirebaseDatabase database = FirebaseDatabase.getInstance();
                  DatabaseReference myRef2 = database.getReference().child("tbl_musteri").child(id);
                  myRef2.addValueEventListener(new ValueEventListener() {
                      @Override
                      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                          keepdata model= new keepdata();
                          model=dataSnapshot.getValue(model.getClass());

                          if (model.gettip().equals("musteri")){
                              Intent intent=new Intent(MainActivity.this,garsonAnasayfa.class);
                              intent.putExtra("mid",id);
                              startActivity(intent);
                          }
                          else if(model.gettip().equals("garson")){
                              Intent intent=new Intent(MainActivity.this,anasayfa.class);
                              startActivity(intent);
                          }
                      }

                      @Override
                      public void onCancelled(@NonNull DatabaseError databaseError) {

                      }
                  });
              }
            }
        });
    }
}
