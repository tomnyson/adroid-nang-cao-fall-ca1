package com.example.androidd_nangg_caoo_ca1_fall;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Bai4Activity extends AppCompatActivity implements LocationListener {
    private TextView txtKetQuaViTri;
    LocationManager locationManager;
    ConnectivityManager connectivityManager;
    Button btnCheckLocation;
    private double mapLong, mapLat;
    private static final int DISTANCE = 100;
    private static final int TIMEOUT = 3000;
    private static final int CHECK_PERMISSION_ACCESS_COARSE_LOCATION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai4);

        txtKetQuaViTri = findViewById(R.id.txtKetQuaViTri);
        btnCheckLocation = findViewById(R.id.btnGetLocation);
        checkPermission();
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);


        connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);



        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, DISTANCE, TIMEOUT, this);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, DISTANCE, TIMEOUT, this);

        btnCheckLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    checkPermission();
                    Location currentLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                txtKetQuaViTri.setText("Vị trí hiện tại là long: " + currentLocation.getLongitude() + "lat: " + currentLocation.getLatitude() );
            }
        });

        NetworkInfo networkInfo = connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileInfo = connectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if(networkInfo != null && networkInfo.isConnected()) {
            Toast.makeText(this, "connect wifi", Toast.LENGTH_SHORT).show();
        }

        if(mobileInfo != null && mobileInfo.isConnected()) {
            Toast.makeText(this, "connect mobile", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        mapLong = location.getLongitude();
        mapLat = location.getLatitude();
        txtKetQuaViTri.setText("Vị trí hiện tại là long: " + mapLong + "lat: " + mapLat );
    }
    public void checkPermission() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            ActivityCompat.requestPermissions(Bai4Activity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    CHECK_PERMISSION_ACCESS_COARSE_LOCATION);
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case CHECK_PERMISSION_ACCESS_COARSE_LOCATION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    Toast.makeText(this, "You have disabled a contacts permission", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }
}
