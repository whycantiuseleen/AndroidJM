package com.example.frank.androidjavamap;

import android.content.Intent;
import android.database.CharArrayBuffer;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

public class Main2Activity extends AppCompatActivity {
    ImageButton artsimage;
    ImageButton cultureimage;
    static DbHelper DbHelper;
    static SQLiteDatabase Db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        setTitle("TravelEasy");

        //TODO 3.1 Create a new instance of SpendingDbHelper
        DbHelper = new DbHelper(this);
        //TODO 3.2 Get an instance of the database that can be written to
        Db = DbHelper.getWritableDatabase();


        final ImageButton artsimage =findViewById(R.id.imageArts);
        final ImageButton cultureimage = findViewById(R.id.imageCulture);

        artsimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextscreen1 = new Intent(Main2Activity.this,ScrollingActivity1.class);
                ActivityOptionsCompat options1 = ActivityOptionsCompat.makeSceneTransitionAnimation(Main2Activity.this,artsimage, ViewCompat.getTransitionName(artsimage));
                startActivity(nextscreen1,options1.toBundle());
//                startActivity(nextscreen1);
            }
        });
        cultureimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextscreen2 = new Intent(Main2Activity.this,ScrollingActivity2.class);
                ActivityOptionsCompat options1 = ActivityOptionsCompat.makeSceneTransitionAnimation(Main2Activity.this,cultureimage, ViewCompat.getTransitionName(cultureimage));
                startActivity(nextscreen2,options1.toBundle());
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);

        MenuItem item = menu.findItem(R.id.spinner);
        Spinner spinner = (Spinner) MenuItemCompat.getActionView(item);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.navibar_array,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setSelection(0);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 1:
                        Intent intent1 = new Intent(Main2Activity.this, Main2Activity.class);
                        startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2 = new Intent(Main2Activity.this,Solver.class);
                        startActivity(intent2);
                        break;
                    case 3:
                        Intent intent3 = new Intent(Main2Activity.this,Check1.class);
                        startActivity(intent3);
                        break;
                    case 4:
                        Intent intent4 = new Intent("showmap",null,Main2Activity.this,MapsActivity.class);
                        startActivity(intent4);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}
