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

    private String specificationAPI;
    private String name, brand, release, dimension, os, storage;

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
        if (bundle != null){
            specificationAPI = bundle.getString("specification");

            AndroidNetworking.get(specificationAPI)
                    .addPathParameter("pageNumber", "0")
                    .addQueryParameter("limit", "3")
                    .addHeaders("token", "1234")
                    .setTag("test")
                    .setPriority(Priority.LOW)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {

                            try {


                                JSONObject object = response.getJSONObject("data");

                                name = object.getString("phone_name");
                                brand = object.getString("brand");
                                release = object.getString("release_date");
                                dimension = object.getString("dimension");
                                os = object.getString("os");
                                storage = object.getString("storage");


                                Log.d("ppppp", "onCreate: " + name +" "+brand );

                            } catch (JSONException e) {
                                e.printStackTrace();


                            }
                        }

                        @Override
                        public void onError(ANError anError){

                            namePhone.setText(name);
                            brandPhone.setText(brand);
                            releasePhone.setText(release);
                            dimensionPhone.setText(dimension);
                            OsPhone.setText(os);
                            storagePhone.setText(storage);
                            Log.d("pppp", "onResponse: erorrr");
                        }
                    });




            Log.d("ppppp", "onCreate: " + specificationAPI);
        }


    }
}