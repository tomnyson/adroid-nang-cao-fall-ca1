package com.example.androidd_nangg_caoo_ca1_fall;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class CaculatorService extends Service {
    public CaculatorService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "onCreate", Toast.LENGTH_LONG).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Double tong = intent.getDoubleExtra("tong",0);
        if(tong >0) {
            Toast.makeText(this, tong+"", Toast.LENGTH_LONG).show();
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "onDestroy", Toast.LENGTH_LONG).show();
    }
}
