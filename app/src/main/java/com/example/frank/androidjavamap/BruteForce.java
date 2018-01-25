package com.example.frank.androidjavamap;

import java.util.*;

public class BruteForce {

    private final Map<String, Integer> map;
    private final Map<Integer, String> reverseMap;

    private double budget;
    private List<Integer> locationsInt;
    private List<Integer> tempLocationsInt;
    private List<Trip> listOfTrips;
    public static final int START_LOCATION = 0;

    private double costOfBestPath;
    private double timeOfBestPath;
    public TravelPath bestPath;


    public static final Double[][] footTime = new Double[][]
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

    public BruteForce(TravelPlanner travelPlanner, double budget) {
        this.budget = budget;
        this.costOfBestPath = Double.MAX_VALUE;
        this.timeOfBestPath = Double.MAX_VALUE;
        map = createMap();
        reverseMap = createReverseMap();

        // Temp list to work on
        tempLocationsInt = new ArrayList<>();
        for (String loc : travelPlanner.getLocations()) {
            tempLocationsInt.add(map.get(loc));
        }

        locationsInt = new ArrayList<>();
        for (String loc : travelPlanner.getLocations()) {
            locationsInt.add(map.get(loc));
        }

        listOfTrips = new ArrayList<>();
        listOfTrips = createAllTrips();
        findAllPaths();
    }

    public List<Trip> createAllTrips() {
        List<Trip> list = new ArrayList<>();

        // Compare every possible trip for all transport and to-and-fro among all locations
        // Ignores trips to itself (pointless)
        // Then adds to list of trips
        for (int i=0; i < tempLocationsInt.size(); i++) {
            for (int j=0; j < tempLocationsInt.size(); j++) {
                if (i == j) continue;
                double fPrice = 0.0;
                double fTime = footTime[tempLocationsInt.get(i)][tempLocationsInt.get(j)];
                double ptPrice = pubPrice[tempLocationsInt.get(i)][tempLocationsInt.get(j)];
                double ptTime = pubTime[tempLocationsInt.get(i)][tempLocationsInt.get(j)];
                double tPrice = taxiPrice[tempLocationsInt.get(i)][tempLocationsInt.get(j)];
                double tTime = taxiTime[tempLocationsInt.get(i)][tempLocationsInt.get(j)];
                Trip tripFoot = new Trip(tempLocationsInt.get(i), tempLocationsInt.get(j),
                        ModeOfTransport.FOOT, fTime, fPrice);
                Trip tripPub = new Trip(tempLocationsInt.get(i), tempLocationsInt.get(j),
                        ModeOfTransport.PUBLIC_TRANSPORT, ptTime, ptPrice);
                Trip tripTaxi = new Trip(tempLocationsInt.get(i), tempLocationsInt.get(j),
                        ModeOfTransport.TAXI, tTime, tPrice);
                list.add(tripFoot);
                list.add(tripPub);
                list.add(tripTaxi);
            }
        }
        return list;
    }

    public List<TravelPath> findAllPaths() {
        List<TravelPath> allPaths = new ArrayList<>();

        LinkedList<Trip> baseTripStack = new LinkedList<>();  // Stack to keep track of bases, upon return to start, add the path
        LinkedList<Trip> workingTripStack = new LinkedList<>(); // Stack to keep track of next trips from top of base
        List<Integer> visitedLocations = new ArrayList<>();

        for (Trip trip : listOfTrips) {
            // For trips starting from start
            if (trip.getFromLocation() == START_LOCATION)  {
                baseTripStack.push(trip);
                visitedLocations.add(START_LOCATION);
            }

            // While the algorithm is not done
            while (!baseTripStack.isEmpty()) {

                // If path loops back to start (meaning all locations covered), add path to list of paths
                if (baseTripStack.getFirst().getToLocation() == START_LOCATION) {
                    TravelPath travelPath = new TravelPath();
                    double costOfPath = 0.0;
                    double timeOfPath = 0.0;
                    for (int i = baseTripStack.size() - 1; i >= 0; i--) {
                        travelPath.extendPath(baseTripStack.get(i));
                        costOfPath += baseTripStack.get(i).getPrice();
                        timeOfPath += baseTripStack.get(i).getTime();
                    }
                    allPaths.add(travelPath);

                    if (costOfPath <= budget && timeOfPath < timeOfBestPath) {
                        costOfBestPath = costOfPath;
                        timeOfBestPath = timeOfPath;
                        bestPath = travelPath;
                    }

                    Trip poppedTrip = baseTripStack.pop();  // Remove trip from base trip
                    poppedTrip.untag();
                    poppedTrip.removeFromTrip();
                    visitedLocations.remove((Integer)poppedTrip.getFromLocation());
                }

                int pushCount = 0;
                for (Trip nextTrip : listOfTrips) {
                    // Check if next trip carries on from previous,
                    // also checks to make sure does not visit location on same path
                    // also checks if top of base stack is not getting previously used next trips
                    if (baseTripStack.getFirst().getToLocation() == nextTrip.getFromLocation() &&
                            !visitedLocations.contains((Integer)nextTrip.getToLocation()) &&
                            !baseTripStack.getFirst().isTagged()) {
                        nextTrip.fromTrip(baseTripStack.getFirst());   // Tag trips on working stack to top of base stack
                        workingTripStack.push(nextTrip);
                        pushCount++;
                    }
                }
                if (pushCount != 0) baseTripStack.getFirst().tag(); // Tag top of base to make sure no repeats

                // No other locations but back to start (check tag to prevent repeats)
                // If didn't push anything onto working stack previously
                if (pushCount == 0 && !baseTripStack.getFirst().isTagged()) {
                    for (Trip nextTrip : listOfTrips) {
                        if (baseTripStack.getFirst().getToLocation() == nextTrip.getFromLocation() &&
                                nextTrip.getToLocation() == START_LOCATION) {
                            nextTrip.fromTrip(baseTripStack.getFirst());
                            workingTripStack.push(nextTrip);
                            baseTripStack.getFirst().tag();
                        }
                    }
                }

                if (!workingTripStack.isEmpty() &&
                        workingTripStack.getFirst().getFromTrip() == baseTripStack.getFirst() &&
                        baseTripStack.getFirst().isTagged()) {
                    baseTripStack.push(workingTripStack.pop());
                } else if (baseTripStack.getFirst().isTagged()) {
                    Trip poppedTrip = baseTripStack.pop();
                    poppedTrip.untag();
                    poppedTrip.removeFromTrip();
                    visitedLocations.remove((Integer)poppedTrip.getFromLocation());
                }

                if (!baseTripStack.isEmpty()) {
                    if (!visitedLocations.contains((Integer)baseTripStack.getFirst().getFromLocation())) {
                        visitedLocations.add((Integer)baseTripStack.getFirst().getFromLocation());
                    }
                }
            }
        }

        return allPaths;
    }

    public List<Trip> getBestPath() {
        List<Trip> path = new ArrayList<>();
        for (Trip trip : bestPath.getPath()) {
            path.add(trip);
        }
        return path;
    }


    public List<Trip> getListOfTrips() {
        return listOfTrips;
    }

    public double getCostOfBestPath() {
        return costOfBestPath;
    }

    public double getTimeOfBestPath() {
        return timeOfBestPath;
    }
}
