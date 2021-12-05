package com.example.grabyourphone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DetailPhoneActivity extends AppCompatActivity {

    private Bundle bundle;


    private ImageView phoneImage;
    private TextView namePhone;
    private TextView brandPhone;
    private TextView releasePhone;
    private TextView dimensionPhone;
    private TextView OsPhone;
    private TextView storagePhone;
    String name, image, slug;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_phone);

        phoneImage = findViewById(R.id.imagePhoneDetail);
        namePhone = findViewById(R.id.phoneNameDetail);
        releasePhone = findViewById(R.id.phoneReleaseDetail);
        dimensionPhone = findViewById(R.id.phoneDimensionDetail);
        OsPhone = findViewById(R.id.phoneOsDetail);
        storagePhone = findViewById(R.id.phoneStorageDetail);
        bundle = getIntent().getExtras();

        name = bundle.getString("name");
        image = bundle.getString("image");
        slug = bundle.getString("detail");

        getData();


        namePhone.setText(name);
        Picasso.get().load(image).into(phoneImage);
    }

    void getData(){
        AndroidNetworking.get("https://api-mobilespecs.azharimm.site/v2/" + slug).build().getAsJSONObject(new JSONObjectRequestListener() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject data = response.getJSONObject("data");
                    String release_date = data.getString("release_date");
                    String dimension = data.getString("dimension");
                    String os = data.getString("os");
                    String storage = data.getString("storage");
                    if (release_date.equals("")){
                        releasePhone.setText("N/A");
                    } else {
                        releasePhone.setText(release_date);
                    }
                    if (dimension.equals("")){
                        dimensionPhone.setText("N/A");
                    } else {
                        dimensionPhone.setText(dimension);
                    }
                    if (os.equals("")){
                        OsPhone.setText("N/A");
                    } else {
                        OsPhone.setText(os);
                    }
                    if (storage.equals("")){
                        storagePhone.setText("N/A");
                    } else {
                        storagePhone.setText(storage);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(ANError anError) {

            }
        });
    }



}