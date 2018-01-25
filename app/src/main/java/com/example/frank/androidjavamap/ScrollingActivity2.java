package com.example.frank.androidjavamap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class ScrollingActivity2 extends AppCompatActivity {
//    ImageButton btnbuddha;
//    ImageButton btnancient;
//    ImageButton btnperanakan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling2);

        setTitle("Culture");
//        ImageButton btnbuddha = (ImageButton) findViewById(R.id.btnbuddha);
//        ImageButton btnancient = (ImageButton) findViewById(R.id.btnancient);
//        ImageButton btnperanakan = (ImageButton) findViewById(R.id.btnperanakan);
//
//
//
//        btnbuddha.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent nextscreen6 = new Intent(ScrollingActivity2.this,CultureActivity1.class);
//                startActivity(nextscreen6);
//            }
//        });
//        btnancient.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent nextscreen7 = new Intent(ScrollingActivity2.this,CultureActivity2.class);
//                startActivity(nextscreen7);
//            }
//        });
//        btnperanakan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent nextscreen8 = new Intent(ScrollingActivity2.this,CultureActivity3.class);
//                startActivity(nextscreen8);
//            }
//        });

    }

    public void tobuddha(View view){
        Intent nextscreen6 = new Intent(ScrollingActivity2.this,CultureActivity1.class);
        startActivity(nextscreen6);

    }

    public void toperanakan (View view){
        Intent nextscreen7 = new Intent(ScrollingActivity2.this,CultureActivity3.class);
        startActivity(nextscreen7);
    }

    public void toancient (View view){
        Intent nextscreen8 = new Intent(ScrollingActivity2.this,CultureActivity2.class);
        startActivity(nextscreen8);
    }
}
