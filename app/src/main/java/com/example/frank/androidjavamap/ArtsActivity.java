package com.example.frank.androidjavamap;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ArtsActivity extends AppCompatActivity {
    Button btnAddArtScience;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arts);

        setTitle("Art Science Museum");

        btnAddArtScience = (Button) findViewById(R.id.btnAddSAM);
//        btnAddArtScience.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ContentValues cv = new ContentValues();
//                cv.put(MarkerContract.MarkerEntry.COL_REMARKS, "marker1");
//                cv.put(MarkerContract.MarkerEntry.COL_AMOUNT, "art");
//
//                Main2Activity.Db.insert(MarkerContract.MarkerEntry.TABLE_NAME, null ,cv );
//
//                Toast.makeText(this,"Marker1 added to PLACES TO GO!", Toast.LENGTH_SHORT).show(); // this is a pop up message
//
//                view.setEnabled(false);
//                //Intent nextscreen12 = new Intent(ArtsActivity.this,Addlocation.class);
//                //startActivity(nextscreen12);
//            }
//        });
    }

        public void addASM(View view){

        ContentValues cv = new ContentValues();
        cv.put(MarkerContract.MarkerEntry.COL_REMARKS, "Art Science Museum");
        cv.put(MarkerContract.MarkerEntry.COL_AMOUNT, "ART");

        Main2Activity.Db.insert(MarkerContract.MarkerEntry.TABLE_NAME, null ,cv );

        Toast.makeText(this,"Art Science Museum added to PLACES TO GO!", Toast.LENGTH_SHORT).show(); // this is a pop up message

        view.setEnabled(false);

    }

    protected void onResume() {
        super.onResume();

        final String SQL_GET_TABLE = "SELECT * FROM " + MarkerContract.MarkerEntry.TABLE_NAME;
        Cursor cursor = Main2Activity.Db.rawQuery(SQL_GET_TABLE, null);
        int indexRemarks = cursor.getColumnIndex((MarkerContract.MarkerEntry.COL_REMARKS));

        btnAddArtScience.setEnabled(true);


        while (cursor.moveToNext()) {
            String myRemarks = cursor.getString(indexRemarks);
            Log.i("frankie","Remarks = " + myRemarks);
            if (myRemarks.equals("Art Science Museum")){
                btnAddArtScience.setEnabled(false);
            }

            //TO DO 3.1 Create a new instance of SpendingDbHelper
//        spendingDbHelper = new SpendingDbHelper(this);
//        TO DO 3.2 Get an instance of the database that can be written to
//        spendingDb = spendingDbHelper.getWritableDatabase();

        }



    }

    public void view(View view){
        Intent intent = new Intent("show1",null,this,MapsActivity.class);
        startActivity(intent);
    }

}
