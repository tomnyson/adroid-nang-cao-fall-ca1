package com.example.androidd_nangg_caoo_ca1_fall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class result extends AppCompatActivity {
    private TextView ketqua;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ketqua = findViewById(R.id.txtketqua);
        Intent intent = getIntent();
        if(intent != null) {
            String tong = intent.getDoubleExtra("tong", 0)+"";
            Log.i("test", "call here"+tong);
            ketqua.setText(tong);
        }
    }
}
