import trains.TrainSystem;
import trains.TripData;

import java.util.List;

public class Tests {
    private static final String GRAPH_STR = "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";

    public static void main(String[] args) {
        TrainSystem trainSystem = initializeTrainSystem();

        runTests(trainSystem);
    }

    private static TrainSystem initializeTrainSystem() {
        TrainSystem trainSystem = new TrainSystem();

        try {
            trainSystem.build(GRAPH_STR);
        } catch (Exception ex) {
            System.out.println("Failed to build the Train System: " + ex.getMessage());
            System.exit(-1);
        }

        return trainSystem;
    }

    private static void runTests(TrainSystem trainSystem) {
        test1(trainSystem);
        test2(trainSystem);
        test3(trainSystem);
        test4(trainSystem);
        test5(trainSystem);

        test6(trainSystem);
        test7(trainSystem);
        test8(trainSystem);
        test9(trainSystem);
        test10(trainSystem);
    }

    private static void test1(TrainSystem trainSystem) {
        runDistanceTest(trainSystem, new String[]{"A", "B", "C"}, 1);
    }

    private static void test2(TrainSystem trainSystem) {
        runDistanceTest(trainSystem, new String[]{"A", "D"}, 2);
    }

    private static void test3(TrainSystem trainSystem) {
        runDistanceTest(trainSystem, new String[]{"A", "D", "C"}, 3);
    }

    private static void test4(TrainSystem trainSystem) {
        runDistanceTest(trainSystem, new String[]{"A", "E", "B", "C", "D"}, 4);
    }

    private static void test5(TrainSystem trainSystem) {
        runDistanceTest(trainSystem, new String[]{"A", "E", "C"}, 5);
    }

    private static void runDistanceTest(TrainSystem trainSystem, String[] stations, int testNumber) {
        System.out.println(String.format("Output #%d: %s", testNumber, trainSystem.computeRouteDistance(stations)));
    }

    private static void test6(TrainSystem trainSystem) {
        List<TripData> tripsData = trainSystem.extractAllTripsBetweenStationsWithMaximumStops("C", "C", 3);
        System.out.println(String.format("Output #6: %s", tripsData.size()));
    }

    private static void test7(TrainSystem trainSystem) {
        List<TripData> tripsData = trainSystem.extractAllTripsBetweenStationsWithExactStops("A", "C", 4);
        System.out.println(String.format("Output #7: %s", tripsData.size()));
    }

    private static void test8(TrainSystem trainSystem) {
        TripData shortestTrip = trainSystem.findShortestTrip("A", "C");
        System.out.println(String.format("Output #8: %s", shortestTrip.getDistance()));
    }

    private static void test9(TrainSystem trainSystem) {
        TripData shortestTrip = trainSystem.findShortestTrip("B", "B");
        System.out.println(String.format("Output #9: %s", shortestTrip.getDistance()));
    }

    private static void test10(TrainSystem trainSystem) {
        List<TripData> tripsData = trainSystem.extractTripsWithDistanceLessThan("C", "C", 30);
        System.out.println(String.format("Output #10: %s", tripsData.size()));
    }
}
