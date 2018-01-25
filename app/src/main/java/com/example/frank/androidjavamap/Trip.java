package com.example.frank.androidjavamap;

import java.util.HashMap;
import java.util.Map;

public class Trip {
    private int fromLocation;
    private int toLocation;
    private ModeOfTransport modeOfTransport;
    private double time;
    private double price;
    private Trip fromTrip;
    private boolean isTagged;
    private Map<Integer, String> map = new HashMap<>();

    private static Map<Integer, String> createReverseMap() {
        Map<Integer,String> map = new HashMap<>();
        map.put(0,"Marina Bay Sands");
        map.put(1,"Art Science Museum");
        map.put(2,"Singapore Art Museum");
        map.put(3,"Red Dot Design Museum");
        map.put(4,"Ancient Civilization Museum");
        map.put(5,"Buddha Tooth Relic Museum");
        map.put(6,"Peranakan Museum");
        return map;
    }

    public Trip(int fromLocation, int toLocation, ModeOfTransport modeOfTransport, double time, double price) {
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.modeOfTransport = modeOfTransport;
        this.time = time;
        this.price = price;
        this.fromTrip = null;
        this.isTagged = false;
        this.map = createReverseMap();
    }

    public double getPrice() {
        return price;
    }

    public double getTime() {
        return time;
    }

    public int getFromLocation() {
        return fromLocation;
    }

    public String getFromLocationString() {
        return map.get((Integer) fromLocation);
    }

    public int getToLocation() {
        return toLocation;
    }

    public String getToLocationString() {
        return map.get((Integer) toLocation);
    }

    public ModeOfTransport getModeOfTransport() {
        return modeOfTransport;
    }

    // Returns true if comparing e.g. A -> B and B -> A
    public boolean isTripInReverse(Trip trip) {
        if (this.getFromLocation() == trip.getToLocation() &&
                this.getToLocation() == trip.getFromLocation() &&
                this.modeOfTransport == trip.getModeOfTransport()) {
            return true;
        }
        return false;
    }

    // For algorithm
    public void fromTrip(Trip trip) {
        this.fromTrip = trip;
    }

    public Trip getFromTrip() {
        return fromTrip;
    }

    public void removeFromTrip() {
        this.fromTrip = null;
    }

    public void tag() {
        isTagged = true;
    }

    public void untag() {
        isTagged = false;
    }

    public boolean isTagged() {
        return isTagged;
    }



    @Override
    public String toString() {
        return "\n"+getFromLocationString() + " to " + getToLocationString() +
                "\nMode of Transport: " + modeOfTransport.toString() + "\n" +
                "Cost: $" + getPrice() + "\n" +
                "Duration: " + getTime() + " mins\n";
    }
}

enum ModeOfTransport {
    FOOT, PUBLIC_TRANSPORT, TAXI
}

