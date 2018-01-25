package com.example.frank.androidjavamap;

/**
 * Created by Frank on 19/11/2017.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.MyViewHolder> {

    private List<Places> placesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, genre;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            genre = (TextView) view.findViewById(R.id.genre);
//            year = (TextView) view.findViewById(R.id.year);
        }
    }


    public PlacesAdapter(List<Places> moviesList) {
        this.placesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.places_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Places movie = placesList.get(position);
        holder.title.setText(movie.getTitle());
        holder.genre.setText(movie.getGenre());
//        holder.year.setText(movie.getYear());
    }

    @Override
    public int getItemCount() {
        return placesList.size();
    }
}
//
//
//package info.androidhive.recyclerviewswipe;
//
///**
// * Created by ravi on 26/09/17.
// */
//
//        import android.content.Context;
//        import android.support.v7.widget.RecyclerView;
//        import android.view.LayoutInflater;
//        import android.view.View;
//        import android.view.ViewGroup;
//        import android.widget.ImageView;
//        import android.widget.RelativeLayout;
//        import android.widget.TextView;
//
//        import com.bumptech.glide.Glide;
//
//        import java.util.List;

//public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.MyViewHolder> {
//    private Context context;
//    private List<Places> placesList;
//
//    public class MyViewHolder extends RecyclerView.ViewHolder {
//        public TextView title,genre;
////        public ImageView thumbnail;
//        public RelativeLayout viewBackground, viewForeground;
//
//        public MyViewHolder(View view) {
//            super(view);
//            title = (TextView) view.findViewById(R.id.title);
//            genre = (TextView) view.findViewById(R.id.genre);
//            viewBackground = view.findViewById(R.id.view_background);
//            viewForeground = view.findViewById(R.id.view_foreground);
//        }
//    }
//
//
//    public PlacesAdapter(Context context, List<Places> placesList) {
//        this.context = context;
//        this.placesList = placesList;
//    }
//
//    @Override
//    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View itemView = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.places_list_row, parent, false);
//
//        return new MyViewHolder(itemView);
//    }
//
//    @Override
//    public void onBindViewHolder(MyViewHolder holder, final int position) {
//        final Places place = placesList.get(position);
////        holder.name.setText(item.getName());
////        holder.description.setText(item.getDescription());
////        holder.price.setText("₹" + item.getPrice());
////
////        Glide.with(context)
////                .load(item.getThumbnail())
////                .into(holder.thumbnail);
//        holder.title.setText(place.getTitle());
//        holder.genre.setText(place.getGenre());
//    }
//
//    @Override
//    public int getItemCount() {
//        return placesList.size();
//    }
//
//    public void removeItem(int position) {
//        placesList.remove(position);
//        // notify the item removed by position
//        // to perform recycler view delete animations
//        // NOTE: don't call notifyDataSetChanged()
//        notifyItemRemoved(position);
//    }
//
//    public void restoreItem(Places places, int position) {
//        placesList.add(position, places);
//        // notify item added by position
//        notifyItemInserted(position);
//    }
//}
