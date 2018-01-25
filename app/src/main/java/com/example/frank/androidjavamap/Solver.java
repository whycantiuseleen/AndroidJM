package com.example.frank.androidjavamap;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class Solver extends AppCompatActivity {
    EditText budgetInput;
    Button btnFastSolver;
    Button btnBruteForce;
    TextView routeResult;
    TextView costResult;
    TextView timeResult;

    Button viewItinerary;

    private ArrayList<String> locations = new ArrayList<>();
    private List<Trip> locations2 = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solver);

        budgetInput = findViewById(R.id.etBudget);
        btnFastSolver = findViewById(R.id.btnFastsolver);
        btnBruteForce = findViewById(R.id.btnBruteForce);
        routeResult = findViewById(R.id.tRoute);
        costResult = findViewById(R.id.tCost);
        timeResult = findViewById(R.id.tTime);
        viewItinerary = findViewById(R.id.btnItinerary);

        viewItinerary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextscreen = new Intent(Solver.this,Check1.class);
                startActivity(nextscreen);
            }
        });
        btnFastSolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (budgetInput.getText().toString() == null) {
                    btnFastSolver.setEnabled(false);
                }else{
                    final String SQL_GET_TABLE = "SELECT * FROM " + MarkerContract.MarkerEntry.TABLE_NAME;
                    Cursor cursor = Main2Activity.Db.rawQuery(SQL_GET_TABLE, null);

                    //Log.i("frankie", "rows = " + cursor.getCount());

                    int indexRemarks = cursor.getColumnIndex((MarkerContract.MarkerEntry.COL_REMARKS));
                    locations.clear();
                    while (cursor.moveToNext()) {
                        String myTitle = cursor.getString(indexRemarks);
                        locations.add(myTitle);
                    }
                    Log.i("EILEEN", "loc = " + locations);
                    TravelPlanner travelplanner = new TravelPlanner(locations);
                    //Double budget = 20.0;
                    Double budget = Double.parseDouble(budgetInput.getText().toString());

                    FastAlgo fastAlgo = new FastAlgo(travelplanner, budget);

                    Double time = fastAlgo.getTimeOfChosenTrip();
                    Double cost = fastAlgo.getPriceOfChosenTrip();
                    List<Trip> bestroute = fastAlgo.getPathTrip();
                    Integer dot = String.valueOf(cost).indexOf(".");
                    String fin = String.valueOf(cost).substring(0, dot + 3);
                    routeResult.setText("Best Route: " + bestroute.toString());
                    costResult.setText("Total Cost: $" + fin);
                    timeResult.setText("Time Taken: " + String.valueOf(time) + "min");
                }
            }
        });

        btnBruteForce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (budgetInput.getText().toString() == null) {
                    btnBruteForce.setEnabled(false);
                }
                else{
                    final String SQL_GET_TABLE = "SELECT * FROM " + MarkerContract.MarkerEntry.TABLE_NAME;
                    Cursor cursor = Main2Activity.Db.rawQuery(SQL_GET_TABLE, null);

                    Log.i("frankie", "rows = " + cursor.getCount());

                    int indexRemarks = cursor.getColumnIndex((MarkerContract.MarkerEntry.COL_REMARKS));
                    locations.clear();
                    locations.add("Marina Bay Sands");
                    while (cursor.moveToNext()) {
                        String myTitle = cursor.getString(indexRemarks);
                        locations.add(myTitle);
                    }
                    Log.i("EILEEN", "loc = " + locations);

                    TravelPlanner travelplanner = new TravelPlanner(locations);
                    //Double budget = 20.0;
                    Double budget = Double.parseDouble(budgetInput.getText().toString());

                    BruteForce bruteForce = new BruteForce(travelplanner, budget);
                    List<Trip> bestPath = bruteForce.getBestPath();
                    Double time = bruteForce.getTimeOfBestPath();
                    Double cost = bruteForce.getCostOfBestPath();
                    System.out.println(bestPath.toString());
                    Log.i("EILEEN", "bestpath=" + bestPath.toString());


                    Integer dot = String.valueOf(cost).indexOf(".");
                    String fin = String.valueOf(cost).substring(0, dot + 3);

                    routeResult.setText("Best Route: " + bestPath.toString());
                    costResult.setText("Total Cost: $" + fin);
                    timeResult.setText("Time Taken: " + String.valueOf(time) + "min");
                }

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
