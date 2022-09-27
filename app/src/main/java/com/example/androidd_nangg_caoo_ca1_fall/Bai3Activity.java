package com.example.androidd_nangg_caoo_ca1_fall;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.loader.content.CursorLoader;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Bai3Activity extends AppCompatActivity {
    private Button btnShow;
    private ListView lvDanhBa;
    private ArrayList<String> list = new ArrayList<>();
    public static final int PERMISSIONS_REQUEST_READ_CONTACTS = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai3);
        btnShow = findViewById(R.id.btnXemDanhBa);
        lvDanhBa = findViewById(R.id.lvContact);

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
        }
    }
    public  void showData() {
        Uri uri = Uri.parse("content://contacts/people");
        CursorLoader loader = new CursorLoader(getBaseContext(), uri,
                null, null,
                null, null);
        Cursor cursor = loader.loadInBackground();
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
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1, list);
        lvDanhBa.setAdapter(adapter);
    }
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
