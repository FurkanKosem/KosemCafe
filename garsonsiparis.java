package com.example.kosemcafe3;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class garsonsiparis extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private ArrayList<String> arrayList;
    private ArrayList<String> arrayList2;
    private ArrayAdapter arrayAdapter;

    private int fiyatToplami = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garsonsiparis);
        String siparisId = getIntent().getExtras().getString("id");
        final ListView siparisonay = findViewById(R.id.siparisonay);
        final Button butononay = findViewById(R.id.butononay);
        arrayList = new ArrayList<>();
        arrayList2 = new ArrayList<>();
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, arrayList);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference mDatabase = database.getReference().child("sepet").child(siparisId);

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayList.clear();

                ArrayList<String> fiyatList = new ArrayList<>();

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String musterıId2 = ds.getKey();
                    sepetdata model = ds.getValue(sepetdata.class);

                        fiyatList.add(model.urunadi);
                        arrayList = getGruplanmisListe(arrayList, model.urunadi);



                    siparisonay.setAdapter(arrayAdapter);
                }

                fiyatToplami = 0;
                for (String urunAdi : fiyatList) {
                    int fiyat = Integer.parseInt(urunAdi.split("\\D+")[1]);
                    fiyatToplami += fiyat;
                }

                TextView toplam = findViewById(R.id.toplam);
                toplam.setText("Toplam Tutar : " + fiyatToplami + " TL");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        butononay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "Sipariş onaylandı.", Toast.LENGTH_LONG).show();

                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            String musterıId2 = ds.getKey();
                            mDatabase.child(musterıId2).child("urundurum").setValue("onaylandı").addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                }
                            });
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });


    }

    private ArrayList<String> getGruplanmisListe(ArrayList<String> arrayList, String item) {
        String bulunanUrunAdi = null;

        for (String s : arrayList) {
            if (s.startsWith(item)) {
                bulunanUrunAdi = s;
                break;
            }
        }

        if (bulunanUrunAdi != null) {
            int index = arrayList.indexOf(bulunanUrunAdi);
            String urunAdi = arrayList.remove(index);

            String yeniUrunAdi = "";

            if (urunAdi.contains(" x ")) {
                String[] urunArr = urunAdi.split(" x ");
                int urunSayisi = Integer.parseInt(urunArr[1]) + 1;
                yeniUrunAdi = urunArr[0] + " x " + urunSayisi;
            } else {
                yeniUrunAdi = urunAdi + " x 2";
            }

            arrayList.add(index, yeniUrunAdi);
        } else {
            arrayList.add(item);
        }
        return arrayList;
    }
}
