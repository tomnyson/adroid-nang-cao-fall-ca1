package com.example.androidd_nangg_caoo_ca1_fall;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class SinhVienService extends Service {
    public SinhVienService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }
}
