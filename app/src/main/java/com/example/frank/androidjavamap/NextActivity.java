package com.example.frank.androidjavamap;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;

import java.util.Map;
import java.util.Stack;

public class NextActivity extends AppCompatActivity {
//    private SpendingDbHelper spendingDbHelper;
//    private SQLiteDatabase spendingDb;
    static DbHelper DbHelper;
    static SQLiteDatabase Db;
    ImageButton add1;
    ImageButton add2;
    ImageButton add3;
    ImageButton add4;
//    boolean bool = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);


        //TO DO 3.1 Create a new instance of SpendingDbHelper
        DbHelper = new DbHelper(this);
        //TO DO 3.2 Get an instance of the database that can be written to
        Db = DbHelper.getWritableDatabase();

        add1 = (ImageButton) findViewById(R.id.imageButton6);
        add2 = (ImageButton) findViewById(R.id.imageButton7);
        add3 = (ImageButton) findViewById(R.id.imageButton8);
        add4 = (ImageButton) findViewById(R.id.imageButton9);

// should not need this as this page is only accessed before all the buttons are pressed.
//        final String SQL_GET_TABLE = "SELECT * FROM " + MarkerContract.MarkerEntry.TABLE_NAME;
//        Cursor cursor = Db.rawQuery(SQL_GET_TABLE, null);
//        int indexRemarks = cursor.getColumnIndex((MarkerContract.MarkerEntry.COL_REMARKS));
//
//
//
//
//        while (cursor.moveToNext()) {
//            String myRemarks = cursor.getString(indexRemarks);
//            switch (myRemarks) {
//                case "marker1":
//                    add1.setEnabled(false);
//                    break;
//                case "marker2":
//                    add2.setEnabled(false);
//                    break;
//                case "marker3":
//                    add3.setEnabled(false);
//                    break;
//                case "marker4":
//                    add4.setEnabled(false);
//                    break;
//                default:
//                    break;
//
//            }

        //TO DO 3.1 Create a new instance of SpendingDbHelper
//        spendingDbHelper = new SpendingDbHelper(this);
//        TO DO 3.2 Get an instance of the database that can be written to
//        spendingDb = spendingDbHelper.getWritableDatabase();

//    }
    }

    @Override
    protected void onResume() {
        super.onResume();

        final String SQL_GET_TABLE = "SELECT * FROM " + MarkerContract.MarkerEntry.TABLE_NAME;
        Cursor cursor = Db.rawQuery(SQL_GET_TABLE, null);
        int indexRemarks = cursor.getColumnIndex((MarkerContract.MarkerEntry.COL_REMARKS));

        add1.setEnabled(true);
        add2.setEnabled(true);
        add3.setEnabled(true);
        add4.setEnabled(true);


        while (cursor.moveToNext()) {
            String myRemarks = cursor.getString(indexRemarks);
            switch (myRemarks) {
                case "marker1":
                    add1.setEnabled(false);
                    break;
                case "marker2":
                    add2.setEnabled(false);
                    break;
                case "marker3":
                    add3.setEnabled(false);
                    break;
                case "marker4":
                    add4.setEnabled(false);
                    break;
                default:
                    break;

            }

            //TO DO 3.1 Create a new instance of SpendingDbHelper
//        spendingDbHelper = new SpendingDbHelper(this);
//        TO DO 3.2 Get an instance of the database that can be written to
//        spendingDb = spendingDbHelper.getWritableDatabase();

        }

    }

    public void view1(View view) {
//        marker1.setVisible(true);
//        Intent intent = new Intent(this, MapsActivity.class);
        Intent intent = new Intent("show1",null,this,MapsActivity.class);
        startActivity(intent);
    }

    public void view2(View view) {
        Intent intent = new Intent("show2",null,this,MapsActivity.class);
        startActivity(intent);
    }

    public void view3(View view) {
        Intent intent = new Intent("show3",null,this,MapsActivity.class);
        startActivity(intent);
    }

    public void view4(View view) {
        Intent intent = new Intent("show4",null,this,MapsActivity.class);
        startActivity(intent);
    }


//how to make sure it can only be added once
    public void add1(View view){
        ContentValues cv = new ContentValues();
        cv.put(MarkerContract.MarkerEntry.COL_REMARKS, "marker1");
        cv.put(MarkerContract.MarkerEntry.COL_AMOUNT, "art");

        Db.insert(MarkerContract.MarkerEntry.TABLE_NAME, null ,cv );

        Toast.makeText(this,"Marker1 added to PLACES TO GO!", Toast.LENGTH_SHORT).show(); // this is a pop up message

        view.setEnabled(false);

    }

    public void add2(View view){
        ContentValues cv = new ContentValues();
        cv.put(MarkerContract.MarkerEntry.COL_REMARKS, "marker2");
        cv.put(MarkerContract.MarkerEntry.COL_AMOUNT, "art");

        Db.insert(MarkerContract.MarkerEntry.TABLE_NAME, null ,cv );

        Toast.makeText(this,"Marker2 added to PLACES TO GO!", Toast.LENGTH_SHORT).show(); // this is a pop up message

        view.setEnabled(false);
    }

    public void add3(View view){

        ContentValues cv = new ContentValues();
        cv.put(MarkerContract.MarkerEntry.COL_REMARKS, "marker3");
        cv.put(MarkerContract.MarkerEntry.COL_AMOUNT, "culture");

        Db.insert(MarkerContract.MarkerEntry.TABLE_NAME, null ,cv );

        Toast.makeText(this,"Marker3 added to PLACES TO GO!", Toast.LENGTH_SHORT).show(); // this is a pop up message

        view.setEnabled(false);
    }

    public void add4(View view){

        ContentValues cv = new ContentValues();
        cv.put(MarkerContract.MarkerEntry.COL_REMARKS, "marker4");
        cv.put(MarkerContract.MarkerEntry.COL_AMOUNT, "culture");

        Db.insert(MarkerContract.MarkerEntry.TABLE_NAME, null ,cv );

        Toast.makeText(this,"Marker4 added to PLACES TO GO!", Toast.LENGTH_SHORT).show(); // this is a pop up message

        view.setEnabled(false);
    }

    public void viewmap(View view){
        Intent intent = new Intent("showmap",null,this,MapsActivity.class);
        startActivity(intent);
    }

    public void viewplaces(View view){
        Intent intent = new Intent(this,Check1.class);
        startActivity(intent);
    }

    public void del1(View view){
        Intent intent = new Intent(this,Main2Activity.class);
        startActivity(intent);
    }

}
