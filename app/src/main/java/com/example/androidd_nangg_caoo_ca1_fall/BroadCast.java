package com.example.androidd_nangg_caoo_ca1_fall;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

public class BroadCast extends BroadcastReceiver {
    private static String action = "com.example.androidd_nangg_caoo_ca1_fall.SEND_DATA";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("TEST", "Call here" + action.equals(intent.getAction()));
        if(action.equals(intent.getAction())) {
            String ten = intent.getStringExtra("mssv");
            Log.i("TEST", "Call here" + ten);
        } else {
            if(isConnectedToInternet(context) == true) {
                Toast.makeText(context, "call here", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "call here 1", Toast.LENGTH_SHORT).show();
            }
            Toast.makeText(context, "call here 1", Toast.LENGTH_LONG).show();

        }


    }
    public boolean isConnectedToInternet(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager!= null){
            NetworkInfo[] info =  connectivityManager.getAllNetworkInfo();
            if (info!=null){
                for (NetworkInfo networkInfo : info) {
                    if (networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
