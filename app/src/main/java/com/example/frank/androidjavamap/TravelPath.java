package com.example.frank.androidjavamap;

import java.util.ArrayList;
import java.util.List;

public class TravelPath {
    private List<Trip> path;

    public TravelPath() {
        this.path = new ArrayList<>();
    }

    public TravelPath(List<Trip> path) {
        this.path = new ArrayList<>();
        this.path = path;
    }

    public void extendPath(Trip trip) {
        path.add(trip);
    }

    public List<Trip> getPath() {
        return path;
    }

    @Override
    public String toString() {
        return path.toString();
    }
}
