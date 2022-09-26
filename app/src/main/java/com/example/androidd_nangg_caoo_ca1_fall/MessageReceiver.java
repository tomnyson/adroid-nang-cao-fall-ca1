package com.example.androidd_nangg_caoo_ca1_fall;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MessageReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(Bai2Activity.ACTON_SEND)) {
        String name = intent.getStringExtra("name");
            Toast.makeText(context, "name: "+ name, Toast.LENGTH_SHORT).show();
        } else {
            Log.i("TEST", "call here"+intent.getAction());
        }

    }
}
