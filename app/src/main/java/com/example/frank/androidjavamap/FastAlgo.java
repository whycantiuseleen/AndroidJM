package com.example.frank.androidjavamap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.frank.androidjavamap.ModeOfTransport.FOOT;
import static com.example.frank.androidjavamap.ModeOfTransport.PUBLIC_TRANSPORT;
import static com.example.frank.androidjavamap.ModeOfTransport.TAXI;


public class FastAlgo{

    private static Map<String, Integer> map;
    private static Map<Integer, String> reverseMap;
    private List<Integer> locationsInt;
    private List<Integer> tempLocationsInt;
    private double timeWeightCount;
    private List<Integer> pathInt;
    private double budget;
    private List<Trip> pathTrip;

    private double priceOfChosenTrip;
    private double timeOfChosenTrip;
    private List<String> locationsOnPathInString;

    private static final Double[][] footTime = new Double[][]
            {       {0.0, 5.0, 29.0, 12.0, 21.0, 32.0, 29.0},
                    {5.0, 0.0, 27.0, 10.0, 18.0, 30.0, 26.0},
                    {29.0, 27.0, 0.0, 30.0, 18.0, 27.0, 6.0},
                    {12.0, 10.0, 30.0, 0.0, 16.0, 20.0, 27.0},
                    {21.0, 18.0, 18.0, 16.0, 0.0, 17.0, 12.0},
                    {32.0, 30.0, 27.0, 20.0, 17.0, 0.0, 22.0},
                    {29.0, 26.0, 6.0, 27.0, 12.0, 22.0, 0.0}
            };
    private static final Double[][] pubPrice = new Double[][]
            {       {0.00, 0.77, 0.87, 0.77, 0.87, 0.87, 0.77},
                    {0.77, 0.00, 0.77, 0.77, 0.77, 0.77, 0.77},
                    {0.77, 0.77, 0.00, 0.87, 0.77, 0.77, 0.77},
                    {0.77, 0.77, 0.77, 0.00, 0.77, 0.77, 0.77},
                    {0.87, 0.77, 0.77, 0.77, 0.00, 0.77, 0.77},
                    {0.87, 0.77, 0.77, 0.77, 0.77, 0.00, 0.77},
                    {0.77, 0.87, 0.77, 0.77, 0.77, 0.77, 0.00}
            };
    private static final Double[][] pubTime = new Double[][]
            {       {0.0, 3.0, 24.0, 5.0, 25.0, 26.0, 18.0},
                    {3.0, 0.0, 17.0, 18.0, 24.0, 22.0, 17.0},
                    {20.0, 22.0, 0.0, 21.0, 18.0, 17.0, 10.0},
                    {5.0, 16.0, 21.0, 0.0, 18.0, 21.0, 21.0},
                    {25.0, 30.0, 19.0, 18.0, 0.0, 18.0, 19.0},
                    {24.0, 21.0, 17.0, 20.0, 18.0, 0.0, 16.0},
                    {19.0, 27.0, 10.0, 21.0, 19.0, 14.0, 0.0}
            };
    private static final Double[][] taxiPrice = new Double[][]
            {       {0.00, 3.60, 4.73, 3.95, 4.71, 4.87, 4.99},
                    {3.60, 0.00, 4.92, 5.00, 4.41, 5.41, 4.68},
                    {4.75, 5.01, 0.00, 4.89, 4.30, 4.74, 3.87},
                    {3.60, 3.83, 5.78, 0.00, 4.73, 4.58, 5.36},
                    {4.76, 4.89, 4.53, 4.18, 0.00, 4.18, 3.96},
                    {4.72, 5.30, 5.88, 4.99, 4.73, 0.00, 4.71},
                    {5.02, 5.32, 3.84, 5.20, 4.61, 5.05, 0.00}
            };
    private static final Double[][] taxiTime = new Double[][]
            {       {0.0, 1.0, 7.0, 3.0, 6.0, 6.0, 6.0},
                    {1.0, 0.0, 6.0, 6.0, 5.0, 7.0, 6.0},
                    {5.0, 6.0, 0.0, 5.0, 4.0, 5.0, 3.0},
                    {2.0, 3.0, 8.0, 0.0, 6.0, 5.0, 7.0},
                    {6.0, 6.0, 5.0, 4.0, 0.0, 4.0, 4.0},
                    {6.0, 7.0, 8.0, 5.0, 6.0, 0.0, 5.0},
                    {7.0, 7.0, 3.0, 7.0, 6.0, 6.0, 0.0}
            };

    private static Double[][] averageTime;

    public FastAlgo(TravelPlanner travelPlanner, double budget) {
        this.budget = budget;
        pathTrip = new ArrayList<>();
        pathInt = new ArrayList<>();
        averageTime = new Double[7][7];
        for (int i=0; i<averageTime.length; i++) {
            for (int j=0; j<averageTime[i].length; j++) {
                averageTime[i][j] = (footTime[i][j] + pubTime[i][j] + taxiTime[i][j])/3.0;
            }
        }

        map = createMap();
        reverseMap = createReverseMap();
        tempLocationsInt = new ArrayList<>();
        for (String loc : travelPlanner.getLocations()) {
            tempLocationsInt.add(map.get(loc));
        }

        locationsInt = new ArrayList<>();
        for (String loc : travelPlanner.getLocations()) {
            locationsInt.add(map.get(loc));
        }


        priceOfChosenTrip = 0.0;
        timeOfChosenTrip = 0.0;
        locationsOnPathInString = new ArrayList<>();
        run();
    }

    // Map locations to index for getting from array
    private static Map<String, Integer> createMap() {
        Map<String,Integer> map = new HashMap<>();
        map.put("Marina Bay Sands",0);
        map.put("Art Science Museum",1);
        map.put("Singapore Art Museum",2);
        map.put("Red Dot Design Museum",3);
        map.put("Ancient Civilization Museum",4);
        map.put("Buddha Tooth Relic Museum",5);
        map.put("Peranakan Museum",6);
        return map;
    }

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

    // Gets a NN-path and stores in path based on average timings between locations
    public void obtainPath(Integer start) {
        // If remaining locations empty, exit method
        if (tempLocationsInt.size()==0) return;

        // Remove current location from remaining locations
        tempLocationsInt.remove(start);
        double smallestWeight = Double.MAX_VALUE;
        int nextLocation = -1;

        // Trip from current to 'selected' location, next location has the smallest edge to it
        for (int select : tempLocationsInt) {
            double currentWeight = averageTime[start][select];
            if (currentWeight < smallestWeight) {
                smallestWeight = currentWeight;
                nextLocation = select;
            }
        }
        if (nextLocation == -1) return; // In case no next location (shouldn't happen)

        timeWeightCount += smallestWeight; // Adds time for trip to next location to the total time
        pathInt.add(nextLocation);

        // Recurse, checking remaining locations starting from next location
        obtainPath(nextLocation);


    }

    public void addStartandEnd(Integer start) {
        // Add start to 2nd vertex
        timeWeightCount += averageTime[start][pathInt.get(0)];
        pathInt.add(0,start);

        // Add 2nd last back to start
        timeWeightCount += averageTime[pathInt.get(pathInt.size()-1)][start];
        pathInt.add(start);
    }

    // Uses NN-path and chooses which mode of transport for each trip
    public void obtainFinalPath() {
        double currentBudget = budget;
        int numberOfTripsLeft = pathInt.size()-1;

        for (int i=0; i<pathInt.size()-1; i++) {
            double averageCostPerTrip = currentBudget/numberOfTripsLeft;
            if (taxiPrice[pathInt.get(i)][pathInt.get(i+1)] < averageCostPerTrip) {
                Trip trip = new Trip(pathInt.get(i), pathInt.get(i+1), TAXI,
                        taxiTime[pathInt.get(i)][pathInt.get(i+1)],
                        taxiPrice[pathInt.get(i)][pathInt.get(i+1)]);
                pathTrip.add(trip);
                currentBudget -= trip.getPrice();
            } else if (pubPrice[pathInt.get(i)][pathInt.get(i+1)] < averageCostPerTrip) {
                Trip trip = new Trip(pathInt.get(i), pathInt.get(i+1), PUBLIC_TRANSPORT,
                        pubTime[pathInt.get(i)][pathInt.get(i+1)],
                        pubPrice[pathInt.get(i)][pathInt.get(i+1)]);
                pathTrip.add(trip);
                currentBudget -= trip.getPrice();
            } else {
                Trip trip = new Trip(pathInt.get(i), pathInt.get(i+1), FOOT,
                        footTime[pathInt.get(i)][pathInt.get(i+1)],
                        0.0);
                pathTrip.add(trip);
            }
            numberOfTripsLeft--;
        }
    }

    public List<Trip> getPathTrip() {
        return pathTrip;
    }

    public void run() {
        obtainPath(0);
        addStartandEnd(0);
        obtainFinalPath();
        List<Trip> tripList = this.getPathTrip();
        for (Trip trip : tripList) {
            priceOfChosenTrip += trip.getPrice();
            timeOfChosenTrip += trip.getTime();
            locationsOnPathInString.add(reverseMap.get(trip.getFromLocation()));
        }
        Trip backToStart = tripList.get(tripList.size()-1);
        locationsOnPathInString.add(reverseMap.get(backToStart.getToLocation()));
    }

    public double getPriceOfChosenTrip() {
        return priceOfChosenTrip;
    }

    public double getTimeOfChosenTrip() {
        return timeOfChosenTrip;
    }

    public List<String> getLocationsOnPathInString() {
        return locationsOnPathInString;
    }
}

