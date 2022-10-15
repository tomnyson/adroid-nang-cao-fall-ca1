package com.example.androidd_nangg_caoo_ca1_fall;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

public class profileActivity extends AppCompatActivity {
    private TextView txtName;
    private ImageView imgAvatar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        txtName = findViewById(R.id.txtNameProfile);
        imgAvatar = findViewById(R.id.imvAvatar);
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        GraphRequest request = GraphRequest.newMeRequest(
                accessToken,
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(
                            JSONObject object,
                            GraphResponse response) {
                        // Application code
                        try {
                            String fullname = object.getString("name");
                            String image = object.getJSONObject("picture").getJSONObject("data").getString("url");
                            Picasso.get().load(image).into(imgAvatar);
                            System.out.println("Name"+fullname);
                            txtName.setText(fullname);


                        }catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,link,picture.typ(large)");
        request.setParameters(parameters);
        request.executeAsync();
    }
}
