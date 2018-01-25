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

public class ArtActivity2 extends AppCompatActivity {
    Button btnAddReddot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_art2);

        setTitle("Red Dot Design Museum");

        btnAddReddot = (Button)findViewById(R.id.btnAddReddot);
//        btnAddReddot.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//        //        Intent nextscreen10 = new Intent(ArtActivity2.this,Addlocation.class);
//         //       startActivity(nextscreen10);
//            }
//        });
    }

    public void addRDDM(View view){

        ContentValues cv = new ContentValues();
        cv.put(MarkerContract.MarkerEntry.COL_REMARKS,"Red Dot Design Museum" );
        cv.put(MarkerContract.MarkerEntry.COL_AMOUNT, "ART");

        Main2Activity.Db.insert(MarkerContract.MarkerEntry.TABLE_NAME, null ,cv );

        Toast.makeText(this,"Red Dot Design Museum added to PLACES TO GO!", Toast.LENGTH_SHORT).show(); // this is a pop up message

        view.setEnabled(false);
    }

    protected void onResume() {
        super.onResume();

        final String SQL_GET_TABLE = "SELECT * FROM " + MarkerContract.MarkerEntry.TABLE_NAME;
        Cursor cursor = Main2Activity.Db.rawQuery(SQL_GET_TABLE, null);
        int indexRemarks = cursor.getColumnIndex((MarkerContract.MarkerEntry.COL_REMARKS));

        btnAddReddot.setEnabled(true);


        while (cursor.moveToNext()) {
            String myRemarks = cursor.getString(indexRemarks);
            Log.i("frankie","Remarks = " + myRemarks);
            if (myRemarks.equals("Red Dot Design Museum")){
                btnAddReddot.setEnabled(false);
            }

            //TO DO 3.1 Create a new instance of SpendingDbHelper
//        spendingDbHelper = new SpendingDbHelper(this);
//        TO DO 3.2 Get an instance of the database that can be written to
//        spendingDb = spendingDbHelper.getWritableDatabase();

        }

    }
    public void view(View view){
        Intent intent = new Intent("show2",null,this,MapsActivity.class);
        startActivity(intent);
    }
}
