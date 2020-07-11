package com.example.kosemcafe3;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class garsonAnasayfa extends AppCompatActivity {
    private Button yemek;
    private Button nargile;
    private Button soguk;
    private Button sicak;
    private Button salata;
    private ImageView sepetekle;
    private Button cıkısyap2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garson_anasayfa);
        final String musteriId= getIntent().getExtras().getString("mid");
        salata=findViewById(R.id.salata);
        salata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(garsonAnasayfa.this,menusalata.class);
                intent.putExtra("mid",musteriId);
                startActivity(intent);
            }
        });


        sicak=findViewById(R.id.sicak);
        sicak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(garsonAnasayfa.this,menusicak.class);
                intent.putExtra("mid",musteriId);
                startActivity(intent);
            }
        });


        soguk=findViewById(R.id.soguk);
        soguk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(garsonAnasayfa.this,menusoguk.class);
                intent.putExtra("mid",musteriId);
                startActivity(intent);
            }
        });


        nargile=findViewById(R.id.nargile);
        nargile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(garsonAnasayfa.this,menunargile.class);
                intent.putExtra("mid",musteriId);
                startActivity(intent);

            }
        });


        yemek=findViewById(R.id.yemek);
        yemek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(garsonAnasayfa.this,menuyemek.class);
                intent.putExtra("mid",musteriId);
                startActivity(intent);
            }
        });

        sepetekle=findViewById(R.id.sepetekle);
        sepetekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(garsonAnasayfa.this,menusepet.class);
                intent.putExtra("mid",musteriId);
                startActivity(intent);
            }
        });

        cıkısyap2=findViewById(R.id.cıkısyap2);
        cıkısyap2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(garsonAnasayfa.this);
                builder.setTitle("ÇIKIŞ YAP");
                builder.setMessage("Çıkış yapmak istiyor musunuz?");
                builder.setCancelable(true);
                builder.setPositiveButton("ÇIKIŞ YAP", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(garsonAnasayfa.this, MainActivity.class);
                        dialog.dismiss();
                        startActivity(intent);
                        finish();


                    }
                });
                builder.setNegativeButton("VAZGEÇ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.show();
            }
        });


    }
}
