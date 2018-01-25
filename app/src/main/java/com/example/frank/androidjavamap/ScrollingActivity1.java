package com.example.frank.androidjavamap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class ScrollingActivity1 extends AppCompatActivity {
    ImageButton btnartscience;
    ImageButton btnreddot;
    ImageButton btnsam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling1);

        setTitle("Arts");
        final ImageButton btnartscience = (ImageButton) findViewById(R.id.btnartscience);
        final ImageButton btnreddot = (ImageButton) findViewById(R.id.btnreddot);
        final ImageButton btnsam = (ImageButton) findViewById(R.id.btnsam);

        btnartscience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextscreen3 = new Intent(ScrollingActivity1.this,ArtsActivity.class);
                ActivityOptionsCompat options3 = ActivityOptionsCompat.makeSceneTransitionAnimation(ScrollingActivity1.this,btnartscience, ViewCompat.getTransitionName(btnartscience));
                startActivity(nextscreen3,options3.toBundle());
            }
        });
        btnreddot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextscreen4 = new Intent(ScrollingActivity1.this,ArtActivity2.class);
                ActivityOptionsCompat options4 = ActivityOptionsCompat.makeSceneTransitionAnimation(ScrollingActivity1.this,btnreddot,ViewCompat.getTransitionName(btnreddot));
                startActivity(nextscreen4,options4.toBundle());
            }
        });

        btnsam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextscreen5 = new Intent(ScrollingActivity1.this,ArtActivity3.class);
                ActivityOptionsCompat options5 = ActivityOptionsCompat.makeSceneTransitionAnimation(ScrollingActivity1.this,btnsam,ViewCompat.getTransitionName(btnsam));
                startActivity(nextscreen5,options5.toBundle());
            }
        });
    }
}
