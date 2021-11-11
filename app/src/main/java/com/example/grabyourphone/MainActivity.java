package com.example.grabyourphone;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public CardView samsung_card1, apple_card2, xiaomi_card3, realme_card4;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        samsung_card1 = (CardView) findViewById(R.id.samsung_card);
        apple_card2 = (CardView) findViewById(R.id.apple_card);
        xiaomi_card3 = (CardView) findViewById(R.id.xiaomi_card);
        realme_card4 = (CardView) findViewById(R.id.realme_card);

        samsung_card1.setOnClickListener(this);
        apple_card2.setOnClickListener(this);
        xiaomi_card3.setOnClickListener(this);
        realme_card4.setOnClickListener(this);


        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_home);

        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_home:



                    case  R.id.nav_search:
                        startActivity(new Intent(getApplicationContext(),SearchActivity.class));
                        finish();
                        overridePendingTransition(0,0);
                        return;

                    case R.id.nav_profile:
                        startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
                        finish();
                        overridePendingTransition(0,0);
                        return;
                }

            }
        });


    }

    @Override
    public void onClick(View v) {

        Intent i;

        switch (v.getId()){
            case R.id.samsung_card:
                i = new Intent(this, SamsungActivity.class);
                startActivity(i);
                break;

            case R.id.apple_card:
                i = new Intent(this, AppleActivity.class);
                startActivity(i);
                break;

            case R.id.xiaomi_card:
                i = new Intent(this, XiaomiActivity.class);
                startActivity(i);
                break;

            case R.id.realme_card:
                i = new Intent(this, RealmeActivity.class);
                startActivity(i);
                break;
        }

    }
}