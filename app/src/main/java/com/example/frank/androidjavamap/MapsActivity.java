package com.example.frank.androidjavamap;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Stack;

/**
 * A styled map using JSON styles from a string resource.
 */
public class MapsActivity extends AppCompatActivity
        implements OnMapReadyCallback {



    private GoogleMap mMap;
    private Marker marker;
    private EditText SearchLoc;
    private Marker start;
    private Marker marker1;
    private Marker marker2;
    private Marker marker3;
    private Marker marker4;
    private Marker marker5;
    private Marker marker6;
    LatLng hotel = new LatLng(1.283943 ,  103.860934);
    LatLng ASM = new LatLng(1.286274,103.859266);
    LatLng RDDM = new LatLng(1.280147,103.856321);
    LatLng SAM = new LatLng(1.297307,103.851062);
    LatLng BTRM = new LatLng(1.281399,103.844268);
    LatLng PM = new LatLng(1.294367,103.849039);
    LatLng ACM = new LatLng(1.287497,103.851386);
    Stack<Marker> marks= new Stack<Marker>();


    private static final String TAG = MapsActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Retrieve the content view that renders the map.
        setContentView(R.layout.activity_maps);

        // Get the SupportMapFragment and register for the callback
        // when the map is ready for use.
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
//        update();

//        back = (ImageButton) findViewById(R.id.back);
//
//        back.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                Intent nextscreen = new Intent(MapsActivity.this, NextActivity.class);
//                startActivity(nextscreen);
//            }
//        });


/////////////////////////////
    }

    /**
     * Manipulates the map when it's available.
     * The API invokes this callback when the map is ready for use.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        // Customise the styling of the base map using a JSON object defined
        // in a string resource file. First create a MapStyleOptions object
        // from the JSON styles string, then pass this to the setMapStyle
        // method of the GoogleMap object.
        boolean success = googleMap.setMapStyle(new MapStyleOptions(getResources()
                .getString(R.string.style_json)));

        if (!success) {
            Log.e(TAG, "Style parsing failed.");
        }

        start = mMap.addMarker(new MarkerOptions().position(hotel).title("Marker in hotel").visible(true).icon(BitmapDescriptorFactory.fromResource(R.drawable.home)));;
        marker1 = mMap.addMarker(new MarkerOptions().position(ASM).title("Marker in Art Science Museum").visible(false));
        marker2 = mMap.addMarker(new MarkerOptions().position(RDDM).title("Marker in Red Dot Design Museum").visible(false));
        marker3 = mMap.addMarker(new MarkerOptions().position(SAM).title("Marker in Singapore Art Museum").visible(false));
        marker4 = mMap.addMarker(new MarkerOptions().position(BTRM).title("Marker in Buddha Tooth Relic Museum").visible(false));
        marker5 = mMap.addMarker(new MarkerOptions().position(PM).title("Marker in Peranakan Museum").visible(false));
        marker6 = mMap.addMarker(new MarkerOptions().position(ACM).title("Marker in Ancient Civilization Museum").visible(false));

        // start at hotel



        String intentaction = getIntent().getAction();
        update();
        switch (intentaction){
            case "show1":
                view(marker1);
                break;
            case "show2":
                view(marker2);
                break;
            case "show3":
                view(marker3);
                break;
            case "show4":
                view(marker4);
                break;
            case "show5":
                view(marker5);
                break;
            case "show6":
                view(marker6);
                break;
            case "showmap":
                view(start);
            default:
                view(start);


//                mMap.moveCamera(CameraUpdateFactory.newLatLng(hotel));
//                mMap.moveCamera(CameraUpdateFactory.zoomTo(zoomLevel));
        }
    }

    public void zoomin(View view){
        mMap.moveCamera(CameraUpdateFactory.zoomBy(1));
    }

    public void zoomout(View view){
        mMap.moveCamera(CameraUpdateFactory.zoomBy(-1));
    }

    public void calculate(View view){
        Intent intent = new Intent(MapsActivity.this,Solver.class);
        startActivity(intent);

    }

//    public void back(View view){
//        if (!marks.empty()) {
//            Marker markertodelete = marks.pop();
//            markertodelete.setVisible(false);
//        }
//        Intent intent = new Intent(this, NextActivity.class);
//        startActivity(intent);
//
//
//    }

    public void add(Marker marker){
        marker.setVisible(true);
    }

    public void del(Marker marker){
        marker.setVisible(false);
    }

///////////////////////// should be placed in the other activity /////////////////////////
    public void view(Marker marker){
        marker.setVisible(true);
        marks.push(marker);
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(marker.getPosition())      // Sets the center of the map to Mountain View
                .zoom(16)
                .bearing(0)                // Sets the orientation of the camera to east
                .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                .build();                   // Creates a CameraPosition from the builder
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

    }



    public void update(){
        final String SQL_GET_TABLE = "SELECT * FROM " + MarkerContract.MarkerEntry.TABLE_NAME;


        Cursor cursor = Main2Activity.Db.rawQuery(SQL_GET_TABLE, null);

        Log.i("frankie", "rows = " + cursor.getCount());


//        Button button123 = (Button) findViewById(R.id.testbutton);

//        String outstring = "Hello";

        int indexRemarks = cursor.getColumnIndex((MarkerContract.MarkerEntry.COL_REMARKS));


        while(cursor.moveToNext()){
            String myRemarks = cursor.getString(indexRemarks);
            switch (myRemarks){
                case "Art Science Museum":
                    add(marker1);
                    break;
                case "Red Dot Design Museum":
                    add(marker2);
                    break;
                case "Singapore Art Museum":
                    add(marker3);
                    break;
                case "Buddha Tooth Relic Museum":
                    add(marker4);
                    break;
                case "Peranakan Museum":
                    add(marker5);
                    break;
                case "Ancient Civilization Museum":
                    add(marker6);
                default:
                    break;

            }

//            outstring = outstring + myRemarks + '\n';


        }
//        Log.i("FRANKIE","THIS IS THE DATABASE" + outstring);
//
//        button123.setText(outstring);

    }

}
