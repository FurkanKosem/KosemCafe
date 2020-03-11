package com.example.kosemcafe;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserLoginActivity extends RegisterActivity {
    private FirebaseAuth auth;
    private FirebaseUser currentUser;


   // EditText email,password;
    Button loginButon;
    Button signup;
    ImageView imageView2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_user_login);

        auth=FirebaseAuth.getInstance ();
        currentUser=auth.getCurrentUser ();

        imageView2= findViewById (R.id.imageView2);
        imageView2.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent (UserLoginActivity.this, MainActivity.class);
                startActivity (intent);

            }
        });

        loginButon =(Button) findViewById (R.id.loginButton);
        loginButon.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                loginUser ();
            }
        });
        signup=findViewById (R.id.signup);
        signup.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent (UserLoginActivity.this, RegisterActivity.class);
                startActivity (intent);
            }
        });
    }

    public void loginUser() {
        loginButon =(Button) findViewById (R.id.loginButton);
        EditText mail = (EditText) findViewById (R.id.email);
        EditText sifre = (EditText) findViewById (R.id.password);


        String Email = mail.getText ().toString ();
        final String Password = sifre.getText ().toString ();

        if (TextUtils.isEmpty ((CharSequence) Email)) {

            Toast.makeText (getApplicationContext (), "Alanları boş geçemezsiniz...!", Toast.LENGTH_LONG).show ();
            return;
        } else if (TextUtils.isEmpty ((CharSequence) Password)) {

            Toast.makeText (getApplicationContext (), "Alanları boş geçemezsiniz...!", Toast.LENGTH_LONG).show ();
            return;
        } else {
            loginButon.setEnabled (false);
            auth.signInWithEmailAndPassword (Email, Password).addOnCompleteListener (new OnCompleteListener<AuthResult> () {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful ()) {
                        Toast.makeText (UserLoginActivity.this, "Giriş Başarılı...!", Toast.LENGTH_LONG).show ();
                        Intent mainIntent = new Intent (UserLoginActivity.this, MainActivity.class);
                        startActivity (mainIntent);
                        finish ();
                    } else {
                        Toast.makeText (UserLoginActivity.this, "Giriş Başarısız...!", Toast.LENGTH_LONG).show ();
                        loginButon.setEnabled (true);
                    }

                }
            });
        }

    }

    public void LogoClick (View view){
        Intent intent=new Intent (getApplicationContext (), MainActivity.class);
        startActivity (intent);

    }

    public void signupClick (View view){
        Intent intent=new Intent (UserLoginActivity.this, RegisterActivity.class);
        startActivity (intent);


    }


    public void LoginButon (View view){
        loginButon=findViewById (R.id.loginButton);
        loginUser ();

    }

    public FirebaseUser getCurrentUser () {
        return currentUser;
    }

    public void setCurrentUser (FirebaseUser currentUser) {
        this.currentUser = currentUser;
    }
}
