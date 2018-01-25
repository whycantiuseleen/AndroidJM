package com.example.frank.androidjavamap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TravelPlanner {

    private List<String> locations;

    public TravelPlanner() {
        locations = new ArrayList<>();
    }

    public TravelPlanner(List<String> locations) {
        this.locations = new ArrayList<>();
        this.locations = locations;
    }

    public void addLocation(String location) {
        if (!locations.contains(location)) {
            locations.add(location);
        }
    }

    public void removeLocation(String location) {
        for (String loc : locations) {
            if (loc.equals(location)) {
                locations.remove(loc);
            }
        }
    }

    public List<String> getLocations() {
        return locations;
    }


}
