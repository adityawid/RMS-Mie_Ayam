package com.kurnniawan.pelayanmanajemenlayananrestaurant;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    AnimationDrawable animationDrawable;
    LinearLayout rootLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

    }

    public void cv1(View view) {
        Intent intent = new Intent(MainActivity.this, InputActivity.class);
        startActivity(intent);
        Toast.makeText(this, "SILAHKAN INPUT PESANAN", Toast.LENGTH_SHORT).show();
    }

    public void cv2(View view) {
        Intent intent = new Intent(MainActivity.this, RiwayatPesananActivity.class);
        startActivity(intent);
    }

    public void cv3(View view) {
        Intent intent = new Intent(MainActivity.this, StokMenuActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }


}
