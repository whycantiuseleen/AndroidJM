package com.example.frank.androidjavamap;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Frank on 15/11/2017.
 */

public class DbHelper extends SQLiteOpenHelper {


    private final Context context;
    private static final int DATABASE_VERSION = 2 ; //whenever we change something on the database, we have to change something
    private SQLiteDatabase sqLiteDatabase;
   
    DbHelper(Context context){
        super(context, MarkerContract.MarkerEntry.TABLE_NAME, null, DATABASE_VERSION );
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

//TODO 2.1 Build the SQLite command string to create the table
        final String SQL_CREATE_TABLE = "CREATE TABLE " + MarkerContract.MarkerEntry.TABLE_NAME
                + "( "
                + MarkerContract.MarkerEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + MarkerContract.MarkerEntry.COL_AMOUNT + " TEXT NOT NULL, "
                + MarkerContract.MarkerEntry.COL_REMARKS + " TEXT NOT NULL )";

        Log.i("Norman", "SQL Command:" + SQL_CREATE_TABLE);

//Execute the SQL command
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //TO DO 2.2 Build the SQLite command string to delete the table
        final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS "
                + MarkerContract.MarkerEntry.TABLE_NAME;

        //TO DO 2.3 execute this SQLite command
        sqLiteDatabase.execSQL(SQL_DELETE_TABLE);
        //TO DO 2.4 Create the table
        onCreate(sqLiteDatabase);
    }

    public void onDowngrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //TO DO 2.2 Build the SQLite command string to delete the table
        final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS "
                + MarkerContract.MarkerEntry.TABLE_NAME;

        //TO DO 2.3 execute this SQLite command
        sqLiteDatabase.execSQL(SQL_DELETE_TABLE);
        //TO DO 2.4 Create the table
        onCreate(sqLiteDatabase);
    }




}