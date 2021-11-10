package com.example.grabyourphone;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public CardView smartphone_card1, headphone_card2, tab_card3, smartwatch_card4;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        smartphone_card1 = (CardView) findViewById(R.id.smartphone_card);
        headphone_card2 = (CardView) findViewById(R.id.headphone_card);
        tab_card3 = (CardView) findViewById(R.id.tab_card);
        smartwatch_card4 = (CardView) findViewById(R.id.smartwatch_card);

        smartphone_card1.setOnClickListener(this);
        headphone_card2.setOnClickListener(this);
        tab_card3.setOnClickListener(this);
        smartwatch_card4.setOnClickListener(this);


        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_home);

        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_home:

                    case R.id.nav_fav:
                        startActivity(new Intent(getApplicationContext(),FavActivity.class));
                        finish();
                        overridePendingTransition(0,0);
                        return;

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
            case R.id.smartphone_card:
                i = new Intent(this, SmartphoneActivity.class);
                startActivity(i);
                break;

            case R.id.headphone_card:
                i = new Intent(this, HeadphoneActivity.class);
                startActivity(i);
                break;

            case R.id.tab_card:
                i = new Intent(this, TabActivity.class);
                startActivity(i);
                break;

            case R.id.smartwatch_card:
                i = new Intent(this, SmartwatchActivity.class);
                startActivity(i);
                break;
        }

    }
}