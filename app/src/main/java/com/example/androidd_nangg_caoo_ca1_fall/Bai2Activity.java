package com.example.androidd_nangg_caoo_ca1_fall;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Bai2Activity extends AppCompatActivity {
    private Button btnSend, btnSendBroadcase;
    private EditText txtName;
    private MessageReceiver messageReceiver = new MessageReceiver();
    public static String ACTON_SEND="com.example.androidd_nangg_caoo_ca1_fall_SEND";
    public static String ACTON_SHOW="show_info";

    private BroadcastReceiver broadcastReceiver = new MessageReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i("TEST", "onReceive");
            if(ACTON_SHOW.equals(intent.getAction())) {
                String ketqua = intent.getStringExtra("name");
                Toast.makeText(context, ketqua, Toast.LENGTH_SHORT).show();
            }
            super.onReceive(context, intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai2);

        LocalBroadcastManager.getInstance(this)
                .registerReceiver(broadcastReceiver,
                new IntentFilter(ACTON_SHOW)
                );

        btnSend = findViewById(R.id.btnSendBroadCast);
        txtName = findViewById(R.id.txtNameSend);
        btnSendBroadcase = findViewById(R.id.btnLocalBroad);

        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTON_SEND);
//        filter.addAction(getPackageName() + "android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(messageReceiver, filter);


//        IntentFilter filter1 = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
//        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
//        registerReceiver(messageReceiver, filter);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ACTON_SEND);
                intent.putExtra("name", txtName.getText().toString());
                sendBroadcast(intent);

            }
        });

        btnSendBroadcase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String congthuc = txtName.getText().toString();
                if(congthuc.contains(",")) {
                    try {
                        String [] phepTinh = congthuc.split(",");
                        Integer soThu1 = Integer.valueOf(phepTinh[0]);
                        Integer soThu2 = Integer.valueOf(phepTinh[1]);
                        Log.i("TEST", soThu1.toString());
                        Log.i("TEST", soThu2.toString());
                        StringBuilder ketqua = new StringBuilder();
                        for (int i=soThu1; i<= soThu2; i++) {
                            if(i % 2 ==0) {
                                ketqua.append(i+", ");
                            }
                        }
                        Log.i("TEST", "click here");
                        Intent intent = new Intent(ACTON_SHOW);
                        intent.putExtra("name", ketqua.toString());
                        LocalBroadcastManager.getInstance(Bai2Activity.this).sendBroadcast(intent);
                    } catch (Exception e) {
                        Toast.makeText(Bai2Activity.this, "Dữ liệu sai", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(messageReceiver);
        // để huỷ broadcat local
        if(broadcastReceiver != null) {
            unregisterReceiver(broadcastReceiver);
        }

    }
}
