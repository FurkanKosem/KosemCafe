package com.example.kosemcafe3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class menusoguk extends AppCompatActivity {
    private ListView sogukliste;
    private ArrayAdapter arrayAdapter;
    private ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menusoguk);
        final String musteriId= getIntent().getExtras().getString("mid");
        sogukliste=(ListView) findViewById(R.id.sogukliste);
        arrayList  =new ArrayList<>();
        arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,android.R.id.text1,arrayList);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mDatabase = database.getReference().child("urun");
        final DatabaseReference mDatabase2 = database.getReference().child("sepet").child(musteriId).push();


        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    dataSnapshot.getKey();
                    urundata model = ds.getValue(urundata.class);
                    if(model.urunkategori.equals("Soğuk İçecek")){
                        arrayList.add(model.urunadi+ " "+ model.urunfiyat);
                    }

                    sogukliste.setAdapter(arrayAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        sogukliste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                HashMap<String,String> urun = new HashMap<>();
                urun.put("urunadi",arrayList.get(position));


                mDatabase2.setValue(urun);

            }
        });


    }
}
