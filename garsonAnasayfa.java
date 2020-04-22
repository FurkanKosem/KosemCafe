package com.example.kosemcafe3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class garsonAnasayfa extends AppCompatActivity {
    private Button yemek;
    private Button nargile;
    private Button soguk;
    private Button sicak;
    private Button salata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garson_anasayfa);

        salata=findViewById(R.id.salata);
        salata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(garsonAnasayfa.this,menusalata.class);
                startActivity(intent);
            }
        });


        sicak=findViewById(R.id.sicak);
        sicak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(garsonAnasayfa.this,menusicak.class);
                startActivity(intent);
            }
        });


        soguk=findViewById(R.id.soguk);
        soguk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(garsonAnasayfa.this,menusoguk.class);
                startActivity(intent);
            }
        });


        nargile=findViewById(R.id.nargile);
        nargile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(garsonAnasayfa.this,menunargile.class);
                startActivity(intent);

            }
        });


        yemek=findViewById(R.id.yemek);
        yemek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(garsonAnasayfa.this,menuyemek.class);
                startActivity(intent);
            }
        });

    }
}
