package com.kurnniawan.pelayanmanajemenlayananrestaurant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class InputActivity extends AppCompatActivity {

    Spinner spinner, spinnerPelayan;
    Button btn;
    EditText znama, zket, zporsi;
    RecyclerView recyclerView;
    String daftarMenu[];
    Map<String, Object> daftarPesanan;
    ListAdapter listAdapter;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        znama = (EditText) findViewById(R.id.etNamaPemesan);
        zket = (EditText) findViewById(R.id.etKeterangan);
        zporsi = (EditText) findViewById(R.id.etPorsi);
        daftarPesanan = new HashMap<>();

        //spinner_menu
        spinner = (Spinner) findViewById(R.id.spinKodeMeja);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.kode_meja, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        //spinner_pelayan
        spinnerPelayan = (Spinner) findViewById(R.id.spinNamaPelayan);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.nama_pelayan, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPelayan.setAdapter(adapter1);

        //recycleview
        recyclerView = (RecyclerView) findViewById(R.id.rv);
        daftarMenu = getResources().getStringArray(R.array.menu_mie);

         listAdapter = new ListAdapter(this, daftarMenu);
        recyclerView.setAdapter(listAdapter);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(lm);

        //databaseReference
        databaseReference = FirebaseDatabase.getInstance().getReference("TERIMA");

        //send button for Firebase Database
        btn = (Button) findViewById(R.id.btnSend);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addPesanan();
            }
        });

    }

    public void addPesanan() {
        String nama_pelayan = spinnerPelayan.getSelectedItem().toString();
        String kode_meja = spinner.getSelectedItem().toString();
        String nama = znama.getText().toString();
        String keterangan = zket.getText().toString();
        //String pesanan = daftarMenu.toString();

        if (nama.isEmpty() || keterangan.isEmpty() || kode_meja.isEmpty() || nama_pelayan.isEmpty()) {
            Toast.makeText(this, "Isi Daftar Pesanan", Toast.LENGTH_SHORT).show();

        } else {
            String id_meja = databaseReference.push().getKey();
            daftarPesanan = new HashMap<>();
            daftarPesanan = listAdapter.getPesanan();
            InputConstructor inputConstructor = new InputConstructor(kode_meja, nama, keterangan, nama_pelayan, System.currentTimeMillis(), daftarPesanan);
            databaseReference.child(id_meja).setValue(inputConstructor);

            //setelah pesanan terkirim kembali ke menu pilihan Activity
            Toast.makeText(this, "Pesanan Terkirim", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(InputActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }




}
