package com.example.androidd_nangg_caoo_ca1_fall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class QLSinhVienActivity extends AppCompatActivity {
    private EditText txtMssv, txtTen, txtDiem;
    private Button btnThem, btnSua, btnXoa;
    private BroadCast broadcast = new BroadCast();
    private static String action = "com.example.androidd_nangg_caoo_ca1_fall.SEND_DATA";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qlsinh_vien);
        txtMssv = findViewById(R.id.txtmssv);
        txtTen = findViewById(R.id.txtten);
        txtDiem = findViewById(R.id.txtdtb);
        btnThem = findViewById(R.id.btnThem);
        IntentFilter filter = new IntentFilter(action);
        registerReceiver(broadcast, filter);
//        broadcast = new BroadCast();
//        IntentFilter filter = new IntentFilter("android.intent.action.AIRPLANE_MODE");
//        registerReceiver(broadcast, filter);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View viei) {
                Log.i("TEST", "call here");
                Intent intent = new Intent(action);
                intent.putExtra("mssv", txtMssv.getText().toString());
                sendBroadcast(intent);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
//        unregisterReceiver(broadcast);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcast);
    }
}
