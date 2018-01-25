package com.example.frank.androidjavamap;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class Check1 extends AppCompatActivity implements RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {

    private static final String TAG = Check1.class.getSimpleName();
    private RecyclerView recyclerView;
    private List<Places> placesList;
    private PlacesAdapter1 mAdapter;
    private CoordinatorLayout coordinatorLayout;

    // url to fetch menu json
    private static final String URL = "https://api.androidhive.info/json/menu.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        recyclerView = findViewById(R.id.recycler_view);
        coordinatorLayout = findViewById(R.id.coordinator_layout);
        placesList = new ArrayList<>();
        mAdapter = new PlacesAdapter1(this, placesList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(mAdapter);

        // adding item touch helper
        // only ItemTouchHelper.LEFT added to detect Right to Left swipe
        // if you want both Right -> Left and Left -> Right
        // add pass ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT as param
        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);


        prepareCart();

        ItemTouchHelper.SimpleCallback itemTouchHelperCallback1 = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                // Row is swiped from recycler view
                // remove it from adapter
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };

        // attaching the touch helper to recycler view
        new ItemTouchHelper(itemTouchHelperCallback1).attachToRecyclerView(recyclerView);
    }

    private void prepareCart() {
        final String SQL_GET_TABLE = "SELECT * FROM " + MarkerContract.MarkerEntry.TABLE_NAME;


        Cursor cursor = Main2Activity.Db.rawQuery(SQL_GET_TABLE, null);

        Log.i("frankie", "rows = " + cursor.getCount());

        int indexRemarks = cursor.getColumnIndex((MarkerContract.MarkerEntry.COL_REMARKS));
        int indexAmount = cursor.getColumnIndex((MarkerContract.MarkerEntry.COL_AMOUNT));
        while(cursor.moveToNext()){
            String myTitle = cursor.getString(indexRemarks);
            String myGenre = cursor.getString(indexAmount);
            Places places = new Places(myTitle,myGenre);
            placesList.add(places);
        }

        mAdapter.notifyDataSetChanged();
    }

    /**
     * callback when recycler view is swiped
     * item will be removed on swiped
     * undo option will be provided in snackbar to restore the item
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof PlacesAdapter1.MyViewHolder) {
            // get the removed item name to display it in snack bar
            String title = placesList.get(viewHolder.getAdapterPosition()).getTitle();
            Log.i("Frankie",Integer.toString(viewHolder.getAdapterPosition()));
            Log.i("frankie" , title);
//            placesList.remove(viewHolder.getAdapterPosition());
//            NextActivity.Db.delete(MarkerContract.MarkerEntry.TABLE_NAME, MarkerContract.MarkerEntry.COL_REMARKS + "="+title,null) >0;
            deleteTitle(title);
            // backup of removed item for undo purpose
            final Places deletedPlace = placesList.get(viewHolder.getAdapterPosition());
            final int deletedIndex = viewHolder.getAdapterPosition();

            // remove the item from recycler view
            mAdapter.removeItem(viewHolder.getAdapterPosition());

//            placesList.remove()
//            Log.i("Frankie",placesList.get(0).getTitle());
////             showing snack bar with Undo option
//            Snackbar snackbar = Snackbar
//                    .make(coordinatorLayout, title + " removed from cart!", Snackbar.LENGTH_LONG)
//                    .setAction("UNDO", new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
////                     undo is selected, restore the deleted item
//                    mAdapter.restoreItem(deletedPlace, deletedIndex);
//                }
//            });
//            snackbar.setActionTextColor(Color.YELLOW);
//            snackbar.show();
//            Snackbar snackbar = Snackbar
//                    .make(coordinatorLayout, "Welcome to AndroidHive", Snackbar.LENGTH_LONG);
//
//            snackbar.show();
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds cartList to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main_menu, menu);
//        return true;
//    }

    public boolean deleteTitle(String name)
    {
        Toast.makeText(this,name + " DELETED!", Toast.LENGTH_SHORT).show(); // this is a pop up message
        return Main2Activity.Db.delete(MarkerContract.MarkerEntry.TABLE_NAME, MarkerContract.MarkerEntry.COL_REMARKS + "='" + name +"'", null) > 0;

    }




}
