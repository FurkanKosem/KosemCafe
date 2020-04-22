package com.example.kosemcafe3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class urunekle extends AppCompatActivity {

    private EditText urunadi;
    private EditText urunkategori;
    private EditText urunfiyat;
    private Button butonekle;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urunekle);


        urunadi=(EditText) findViewById(R.id.urunadi);
        urunkategori=(EditText) findViewById(R.id.urunkategori);
        urunfiyat=(EditText) findViewById(R.id.urunfiyat);
        butonekle=(Button) findViewById(R.id.butonekle);
        butonekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String uruna=urunadi.getText().toString();
                String urunk=urunkategori.getText().toString();
                String urunf=urunfiyat.getText().toString();
                mDatabase= FirebaseDatabase.getInstance().getReference().child("urun").push();
                HashMap<String,String> urun = new HashMap<>();
                urun.put("urunadi",uruna);
                urun.put("urunkategori",urunk);
                urun.put("urunfiyat",urunf);
                mDatabase.setValue(urun);

            }
        });

    }
}
