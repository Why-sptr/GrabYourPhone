package com.example.grabyourphone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class AppleActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DataAdapter adapter;
    private ArrayList<ModelData> listData;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apple);

        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progress_bar);


        progressBar.setVisibility(View.VISIBLE);
        getData();

    }

    private void getData(){
        AndroidNetworking.get("https://api-mobilespecs.azharimm.site/v2/brands/apple-phones-48?page=2")
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

                            listData = new ArrayList<>();

                            JSONObject jsonObject = response.getJSONObject("data");
                            JSONArray jsonArray = jsonObject.getJSONArray("phones");

                            for (int i = 0; i <jsonArray.length(); i++) {
                                JSONObject object = jsonArray.getJSONObject(i);
                                String phone_name = object.getString("phone_name");
                                String brand = object.getString("brand");
                                String image = object.getString("image");
                                String specification = object.getString("detail");

                                listData.add(new ModelData(phone_name, brand, image, specification));

                                Log.d("ddddd", "onResponse: " + phone_name +" "+ brand);
                            }

                            progressBar.setVisibility(View.GONE);

                            adapter = new DataAdapter(listData, new DataAdapter.Callback() {
                                @Override
                                public void onClick(int position) {
                                    Intent intent = new Intent(getApplicationContext(), DetailPhoneActivity.class);
                                    intent.putExtra("specification", listData.get(position).getSpecification());
                                    startActivity(intent);
                                }
                            });
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                            recyclerView.setAdapter(adapter);
                            recyclerView.setLayoutManager(layoutManager);

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