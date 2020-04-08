package com.example.kosemcafe3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


public class kayitol extends AppCompatActivity {
    private EditText txtUsernameRegister;
    private EditText txtEmailRegister;
    private EditText txtPasswordRegister;
    private EditText tip;
    private Button buttonRegister;
    private Button buttonLogin;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private DatabaseReference mDatabase2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayitol);
        buttonRegister=(Button)findViewById(R.id.buttonRegister);
        buttonLogin=(Button)findViewById(R.id.buttonLogin);

        txtEmailRegister=(EditText) findViewById(R.id.txtEmailRegister);
        txtUsernameRegister=(EditText) findViewById(R.id.txtUsernameRegister);
        txtPasswordRegister=(EditText) findViewById(R.id.txtPasswordRegister);
        tip=(EditText) findViewById(R.id.tip);
        mAuth=FirebaseAuth.getInstance();

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ad=txtUsernameRegister.getText().toString();
                String email=txtEmailRegister.getText().toString();
                String sifre=txtPasswordRegister.getText().toString();
                String tipi=tip.getText().toString();
                register_user(ad,email,sifre,tipi);
            }
        });
    }

    private void register_user(final String ad,final String email,final String sifre, final String tipi) {
        mAuth.createUserWithEmailAndPassword(email,sifre).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    String id =mAuth.getCurrentUser().getUid();
                    mDatabase= FirebaseDatabase.getInstance().getReference().child("tbl_musteri").child(id);
                    HashMap<String,String> musteri = new HashMap<>();
                    musteri.put("adi",ad);
                    musteri.put("email",email);
                    musteri.put("sifre",sifre);
                    musteri.put("tip",tipi);
                    mDatabase.setValue(musteri).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Intent intent=new Intent(kayitol.this,anasayfa.class);
                                startActivity(intent);
                            }
                        }
                    });



                }
                else{

                }
            }
        });



    }
}
