package com.kurnniawan.pelayanmanajemenlayananrestaurant;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RiwayatPesananActivity extends AppCompatActivity {

    DatabaseReference databaseReference;
    ListView listView;
    List<InputConstructor> inputConstructorList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat_pesanan);

        databaseReference = FirebaseDatabase.getInstance().getReference("TERIMA");

        listView = (ListView) findViewById(R.id.listViewPesanan);

        inputConstructorList = new ArrayList<>();
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                inputConstructorList.clear();
                for (DataSnapshot riwayatSnapshot : dataSnapshot.getChildren()) {
                    InputConstructor inputConstructor = riwayatSnapshot.getValue(InputConstructor.class);

                    inputConstructorList.add(inputConstructor);
                }
                RiwayatList adapter = new RiwayatList(RiwayatPesananActivity.this, inputConstructorList);
                listView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
