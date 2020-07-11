package com.example.kosemcafe3;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class menusalata extends AppCompatActivity {
    private ListView salataliste;
    private ArrayAdapter arrayAdapter;
    private ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menusalata);
        final String musteriId= getIntent().getExtras().getString("mid");
        salataliste=(ListView) findViewById(R.id.salataliste);
        arrayList  =new ArrayList<>();
        arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,android.R.id.text1,arrayList);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mDatabase = database.getReference().child("urun");
        final DatabaseReference mDatabase2 = database.getReference().child("sepet").child(musteriId);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    dataSnapshot.getKey();
                    urundata model = ds.getValue(urundata.class);
                    if(model.urunkategori.equals("Salata")){
                        arrayList.add(model.urunadi+ " "+ model.urunfiyat);
                    }

                    salataliste.setAdapter(arrayAdapter);
                }
            }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        salataliste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String,String> urun = new HashMap<>();

                String urunadi = arrayList.get(position);
                int fiyat = Integer.parseInt(arrayList.get(0).split("\\D+")[1]);
                menuyemek.toplamFiyat += fiyat;
                urun.put("urunadi", urunadi);
                urun.put("urundurum","Onaylama bekleniyor");


                mDatabase2.push().setValue(urun);
            }
        });

    }
}
