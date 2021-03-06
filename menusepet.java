package com.example.kosemcafe3;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class menusepet extends AppCompatActivity {

    private ListView sepetliste;
    private ArrayAdapter arrayAdapter;
    private ArrayList<String> arrayList;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private DatabaseReference mDatabase2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menusepet);

        sepetliste = (ListView) findViewById(R.id.sepetliste);
        final String musteriId = getIntent().getExtras().getString("mid");
        arrayList = new ArrayList<>();
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, arrayList);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference mDatabase2 = database.getReference().child("sepet").child(musteriId);

        mDatabase2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    dataSnapshot.getKey();
                    sepetdata model = ds.getValue(sepetdata.class);

                    arrayList = getGruplanmisListe(arrayList, model.urunadi);

                    sepetliste.setAdapter(arrayAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        sepetliste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String, String> urun = new HashMap<>();
                urun.put("urunadi", arrayList.get(position));

                mDatabase2.push().setValue(urun);
            }
        });


    }

    private ArrayList<String> getGruplanmisListe(ArrayList<String> arrayList, String item){
        String bulunanUrunAdi = null;

        for (String s : arrayList) {
            if (s.startsWith(item)){
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