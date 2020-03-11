package com.example.kosemcafe;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;



public class RegisterActivity extends AppCompatActivity {

    private EditText txtUsername,txtEmail,txtPassword;

    private FirebaseAuth auth;

    public void init(){
        auth = FirebaseAuth.getInstance ();

        txtUsername = (EditText) findViewById (R.id.txtUsernameRegister);
        txtEmail = (EditText) findViewById (R.id.txtEmailRegister);
        txtPassword = (EditText) findViewById (R.id.txtPasswordRegister);
        Button buttonRegister = (Button) findViewById (R.id.buttonRegister);

        buttonRegister.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                createNewAccount();
            }
        });

        Button buttonLogin= findViewById (R.id.buttonLogin);
        buttonLogin.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (RegisterActivity.this,UserLoginActivity.class);
                startActivity (intent);
            }
        });

    }

    private void createNewAccount() {

        String username = txtUsername.getText ().toString ();
        String email = txtEmail.getText ().toString ();
        String password = txtPassword.getText ().toString ();

        if (TextUtils.isEmpty ((CharSequence) username)) {

            Toast.makeText (this, "Ad soyad alanını boş geçemezsiniz...!", Toast.LENGTH_LONG).show ();
            return;
        } else if (TextUtils.isEmpty ((CharSequence) email)) {

            Toast.makeText (this , "E-Mail alanını boş geçemezsiniz...!", Toast.LENGTH_LONG).show ();
            return;
        }else if (TextUtils.isEmpty ((CharSequence) password)){

            Toast.makeText (this, "Şifre alanını boş geçemezsiniz...!", Toast.LENGTH_LONG).show ();
            return;
        }else{
            auth.createUserWithEmailAndPassword (email,password).addOnCompleteListener (new OnCompleteListener<AuthResult> () {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful ()){
                        Toast.makeText (RegisterActivity.this, "Hesabınız Oluşturuldu...!", Toast.LENGTH_LONG).show ();
                        Intent registerIntent = new Intent (RegisterActivity.this,UserLoginActivity.class);
                        startActivity (registerIntent);
                        finish ();
                        return;
                    }else{
                        Toast.makeText (RegisterActivity.this, "Kayıt Başarısız...!", Toast.LENGTH_LONG).show ();
                    }

                }
            });
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_register);
        init ();

    }

    public void accoutLogin(View view) {
        Intent intent = new Intent (RegisterActivity.this, UserLoginActivity.class);
        startActivity (intent);

    }
}