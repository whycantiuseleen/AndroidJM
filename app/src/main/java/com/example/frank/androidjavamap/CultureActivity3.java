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

public class CultureActivity3 extends AppCompatActivity {
    Button btnAddPeranakanIt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_culture3);
        setTitle("Peranakan Museum");

        btnAddPeranakanIt = (Button) findViewById(R.id.btnAddPeranakan);
    }
    public void addPM(View view){

        ContentValues cv = new ContentValues();
        cv.put(MarkerContract.MarkerEntry.COL_REMARKS, "Peranakan Museum");
        cv.put(MarkerContract.MarkerEntry.COL_AMOUNT, "CULTURE");

        Main2Activity.Db.insert(MarkerContract.MarkerEntry.TABLE_NAME, null ,cv );

        Toast.makeText(this,"Peranakan Museum added to PLACES TO GO!", Toast.LENGTH_SHORT).show(); // this is a pop up message

        view.setEnabled(false);

    }

    protected void onResume() {
        super.onResume();

        final String SQL_GET_TABLE = "SELECT * FROM " + MarkerContract.MarkerEntry.TABLE_NAME;
        Cursor cursor = Main2Activity.Db.rawQuery(SQL_GET_TABLE, null);
        int indexRemarks = cursor.getColumnIndex((MarkerContract.MarkerEntry.COL_REMARKS));

        btnAddPeranakanIt.setEnabled(true);


        while (cursor.moveToNext()) {
            String myRemarks = cursor.getString(indexRemarks);
            Log.i("frankie","Remarks = " + myRemarks);
            if (myRemarks.equals("Peranakan Museum")){
                btnAddPeranakanIt.setEnabled(false);
            }

            //TO DO 3.1 Create a new instance of SpendingDbHelper
//        spendingDbHelper = new SpendingDbHelper(this);
//        TO DO 3.2 Get an instance of the database that can be written to
//        spendingDb = spendingDbHelper.getWritableDatabase();

        }



    }

    public void view(View view){
        Intent intent = new Intent("show5",null,this,MapsActivity.class);
        startActivity(intent);
    }




}
