package com.example.androidd_nangg_caoo_ca1_fall;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.loader.content.CursorLoader;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.provider.Browser;
import java.util.ArrayList;

public class Bai3Activity extends AppCompatActivity {
    private Button btnShow, btnShowLog, btnXemLichSuWeb;
    private ListView lvDanhBa;
    private ArrayList<String> list = new ArrayList<>();
    public static final int PERMISSIONS_REQUEST_READ_CONTACTS = 1;
    public static final int PERMISSIONS_REQUEST_READ_CALL_LOG = 2;
    public static final int PERMISSIONS_REQUEST_READ_HISTORY_BOOKMARK =3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai3);
        btnShow = findViewById(R.id.btnXemDanhBa);
        lvDanhBa = findViewById(R.id.lvContact);
        btnShowLog = findViewById(R.id.btnXemLichSu);
        btnXemLichSuWeb= findViewById(R.id.btnXemHistoryWeb);
        // xử lý show thông tin danh bạ ở đây
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    checkPermission();
                } catch (Exception e) {
                    Toast.makeText(Bai3Activity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.i("TEST", e.getMessage());
                }
            }
        });

        btnShowLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(Bai3Activity.this,
                        Manifest.permission.READ_CALL_LOG) == PackageManager.PERMISSION_DENIED) {
                    ActivityCompat.requestPermissions(Bai3Activity.this,
                            new String[] { android.Manifest.permission.READ_CALL_LOG }, PERMISSIONS_REQUEST_READ_CALL_LOG);
                }
                else {
                    showHistory();
                }
            }
        });
        btnXemLichSuWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
////                if (ContextCompat.checkSelfPermission(Bai3Activity.this,
////                        Manifest.permission.RE) == PackageManager.PERMISSION_DENIED) {
////                    ActivityCompat.requestPermissions(Bai3Activity.this,
////                            new String[] { Manifest.permission. }, PERMISSIONS_REQUEST_READ_HISTORY_BOOKMARK);
////                }
//                else {
//                    showHistory();
//                }
//                showHistoryWeb();
            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSIONS_REQUEST_READ_CONTACTS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    showData();
                } else {
                    Toast.makeText(this, "You have disabled a contacts permission", Toast.LENGTH_LONG).show();
                }
                return;
            }
            case PERMISSIONS_REQUEST_READ_CALL_LOG: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    showHistory();
                } else {
                    Toast.makeText(this, "You have disabled a call log permission", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }
    public  void showData() {
        Uri uri = Uri.parse("content://contacts/people");
//        CursorLoader loader = new CursorLoader(getBaseContext(), uri,
//                null, null,
//                null, null);
//        Cursor cursor = loader.loadInBackground();
        Cursor cursor = getContentResolver().query(uri,  null, null,
                null, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            String colName = ContactsContract.Contacts.DISPLAY_NAME;
            String colId = ContactsContract.Contacts._ID;
            int id = cursor.getColumnIndex(colId);
            int idxName = cursor.getColumnIndex(colName);
            String name = cursor.getString(idxName);
            list.add("Id: " + id + "-" + name);
            Log.i("TEST", id+""+name);
            cursor.moveToNext();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(),
                android.R.layout.simple_list_item_1, list);
        lvDanhBa.setAdapter(adapter);
    }

    @SuppressLint("Range")
    public  void showHistory() {
//          Uri uri = Uri.parse("content://contacts/people");
//        CursorLoader loader = new CursorLoader(getBaseContext(), uri,
//                null, null,
//                null, null);
//        Cursor cursor = loader.loadInBackground();
        Cursor cursor = getContentResolver().query(CallLog.Calls.CONTENT_URI,
                null, null,
                null, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
             String date = cursor.getString(cursor.getColumnIndex(CallLog.Calls.DATE));
             Log.i("test", date);
             String number =cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER));
            String duration =cursor.getString(cursor.getColumnIndex(CallLog.Calls.DURATION));
            list.add("phone:" + number + "time: "+ duration + "date call:" + date);

            cursor.moveToNext();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(),
                android.R.layout.simple_list_item_1, list);
        lvDanhBa.setAdapter(adapter);
    }

//    @SuppressLint("Range")
//    public  void showHistoryWeb() {
//          Uri uri = Uri.parse("content://browser/bookmarks");
////        CursorLoader loader = new CursorLoader(getBaseContext(), uri,
////                null, null,
////                null, null);
////        Cursor cursor = loader.loadInBackground();
//                null, null,
//                null, null);
//        cursor.moveToFirst();
//        while (cursor.isAfterLast() == false) {
//            String title = cursor.getString(cursor.getColumnIndex("title"));
//            Log.i("test", title);
//            String url =cursor.getString(cursor.getColumnIndex("url"));
//            list.add("ten website:" + title + "address: "+ url );
//
//            cursor.moveToNext();
//        }
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(),
//                android.R.layout.simple_list_item_1, list);
//        lvDanhBa.setAdapter(adapter);
//    }

    public void checkPermission() {
        if (ContextCompat.checkSelfPermission(Bai3Activity.this,
                android.Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(Bai3Activity.this,
                    new String[] { android.Manifest.permission.READ_CONTACTS }, PERMISSIONS_REQUEST_READ_CONTACTS);
        }
        else {
            showData();
        }
    }

}
