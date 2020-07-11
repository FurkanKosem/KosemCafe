package com.example.kosemcafe3;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class anasayfa extends AppCompatActivity {

    private Button button5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anasayfa);
        button5 = findViewById(R.id.button5);
        Button button2= findViewById(R.id.button2);
        Button cıkısyap= findViewById(R.id.cıkısyap);
        Button button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(anasayfa.this,gelensiparisler.class);
                startActivity(intent);

            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(anasayfa.this,urunekle.class);
                startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(anasayfa.this,bekleyensiparisler.class);
                startActivity(intent);
            }
        });
        cıkısyap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(anasayfa.this);
                builder.setTitle("ÇIKIŞ YAP");
                builder.setMessage("Çıkış yapmak istiyor musunuz?");
                builder.setCancelable(true);
                builder.setPositiveButton("ÇIKIŞ YAP", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(anasayfa.this, MainActivity.class);
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
