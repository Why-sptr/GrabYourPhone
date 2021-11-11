package com.example.grabyourphone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RealmeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apple);

        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progress_bar);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        progressBar.setVisibility(View.VISIBLE);
        getData();

    }

    private void getData(){
        AndroidNetworking.get("https://api-mobilespecs.azharimm.site/v2/brands/realme-phones-118?page=1")
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
                            JSONObject jsonObject = response.getJSONObject("data");
                            JSONArray jsonArray = jsonObject.getJSONArray("phones");

                            ModelData[] modelData = new ModelData[0];
                            for (int i = 0; i <jsonArray.length(); i++) {
                                JSONObject object = jsonArray.getJSONObject(i);
                                String phone_name = object.getString("phone_name");
                                String brand = object.getString("brand");
                                String image = object.getString("image");

                                modelData = new ModelData[]{(new ModelData(phone_name, brand, image))};
                            }
                            progressBar.setVisibility(View.GONE);

                            DataAdapter myMovieAdapter = new DataAdapter(modelData,RealmeActivity.this);
                            recyclerView.setAdapter(myMovieAdapter);

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