package com.example.frank.androidjavamap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    ImageButton artsimage;
    ImageButton cultureimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("TravelEasy");

        final ImageButton artsimage =(ImageButton)findViewById(R.id.imageArts);
        final ImageButton cultureimage = (ImageButton)findViewById(R.id.imageCulture);

        artsimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextscreen1 = new Intent(MainActivity.this,ScrollingActivity1.class);
                ActivityOptionsCompat options1 = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this,artsimage, ViewCompat.getTransitionName(artsimage));
//                startActivity(nextscreen1,options1.toBundle());
                startActivity(nextscreen1);
            }
        });
        cultureimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextscreen2 = new Intent(MainActivity.this,ScrollingActivity2.class);
                ActivityOptionsCompat options1 = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this,cultureimage, ViewCompat.getTransitionName(cultureimage));
                startActivity(nextscreen2);
            }
        });


    }
}