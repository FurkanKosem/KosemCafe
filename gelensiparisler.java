package com.example.kosemcafe3;

import android.content.Intent;
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

public class gelensiparisler extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private ArrayList<String> arrayList;
    private ArrayList<String> arrayList2;
    private ArrayAdapter arrayAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gelensiparisler);
        final ListView gelensiparisler = findViewById(R.id.gelensiparisler);
        arrayList = new ArrayList<>();
        arrayList2 = new ArrayList<>();
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, arrayList);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        final DatabaseReference mDatabase2 = database.getReference().child("tbl_musteri");

        mDatabase2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayList.clear();
                for (DataSnapshot ds2 : dataSnapshot.getChildren()) {
                    final String musterıId2 = ds2.getKey();
                    final keepdata model = ds2.getValue(keepdata.class);
                    final DatabaseReference mDatabase3 = database.getReference().child("sepet");

                    mDatabase3.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                String musteriId = ds.getKey();
                                if (musteriId.equals(musterıId2)){

                                        arrayList.add(model.adi);
                                        arrayList2.add(musterıId2);


                                    gelensiparisler.setAdapter(arrayAdapter);
                                }


                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });



                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        gelensiparisler.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(gelensiparisler.this, onaylanansiparis.class);
                intent.putExtra("id", arrayList2.get(position));
                startActivity(intent);
            }
        });

    }
}
