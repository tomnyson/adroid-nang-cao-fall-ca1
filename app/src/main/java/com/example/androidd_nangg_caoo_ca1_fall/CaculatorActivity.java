package com.example.androidd_nangg_caoo_ca1_fall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CaculatorActivity extends AppCompatActivity {
   private EditText sothu1, sothu2;
   private Button btnKetqua;
   private  Button btnStartService, btnStopService;
   private Intent intentService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caculator);
        sothu1 = findViewById(R.id.sothu1);
        sothu2= findViewById(R.id.sothu2);
        btnKetqua = findViewById(R.id.btnKetqua);

        btnKetqua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Double so1 = Double.parseDouble(sothu1.getText().toString());
                    Double so2 = Double.parseDouble(sothu2.getText().toString());

                    Double tong  =  so1 + so2;
                    Log.i("test", "call here"+tong);
                    Intent intent = new Intent(CaculatorActivity.this,result.class);
                    intent.putExtra("tong", tong);
                    startActivity(intent);
                } catch (Exception e) {

                }
            }
        });
        btnStopService = findViewById(R.id.btnStopService);
        btnStartService = findViewById(R.id.startService);
        btnStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Double so1 = Double.parseDouble(sothu1.getText().toString());
                    Double so2 = Double.parseDouble(sothu2.getText().toString());
                    Double tong  =  so1 + so2;

                    intentService = new Intent(CaculatorActivity.this, CaculatorService.class);
                    startService(intentService);
                    intentService.putExtra("tong", tong);
                    startService(intentService);

                    // handle intent

                    Intent localIntent = new Intent(CaculatorActivity.this, LocalIntentService.class);
                    startService(localIntent);
                } catch (Exception e) {
                    Toast.makeText(CaculatorActivity.this, "dữ liệu không hợp lệ", Toast.LENGTH_SHORT).show();
                }

            }
        });
        btnStopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(intentService);
            }
        });


    }
}
