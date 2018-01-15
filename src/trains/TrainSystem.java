package trains;

import java.util.*;

public class TrainSystem {
    private static final String NO_SUCH_ROUTE = "NO SUCH ROUTE";

    // The original graph
    private Graph graph;

    // The trip processor that computes the trips queries
    private TripsProcessor tripsProcessor;

    public TrainSystem() {
        graph = new Graph();
        tripsProcessor = new TripsProcessor(graph);
    }

    // Build the graph by parsing the original graph and building the entire information of all possible routes
    public void build(String graphStr) {
        graph.build(graphStr);
    }

    // Compute the overall distance of direct travel between stations.
    // Return -1 if no such exists
    public String computeRouteDistance(String[] stations) {
        if (stations.length < 2) {
            return NO_SUCH_ROUTE;
        }

        Integer distance = 0;
        for (int i = 0; i < stations.length-1; i++) {
            Integer localDistance = graph.getDistance(stations[i], stations[i+1]);
            if (localDistance == null) {
                return NO_SUCH_ROUTE;
            }
            distance += localDistance;
        }

        return distance.toString();
    }

    // Extract all the possible trips between 2 stops with restrictions on the maximnum number of stops
    public List<TripData> extractAllTripsBetweenStationsWithMaximumStops(String source, String target, int requiredStops) {
        return tripsProcessor.findTripsWithRestriction(source, target, requiredStops, TripsProcessor.RestrictionType.STOPS_MAXIMUM);
    }

    // Extract all the possible trips between 2 stops with restrictions on the exact number of stops
    public List<TripData> extractAllTripsBetweenStationsWithExactStops(String source, String target, int requiredStops) {
        return tripsProcessor.findTripsWithRestriction(source, target, requiredStops, TripsProcessor.RestrictionType.STOPS_EXACT);
    }

    // Find the shortest trip between 2 stations (null if no such exists)
    public TripData findShortestTrip(String source, String target) {
        return tripsProcessor.findTripsWithRestriction(source, target, 0, TripsProcessor.RestrictionType.SHORTEST_TRIP)
                .stream()
                .min(Comparator.comparing(TripData::getDistance))
                .orElse(null);
    }

    public List<TripData> extractTripsWithDistanceLessThan(String source, String target, int distance) {
        TripsProcessor tripsProcessor = new TripsProcessor(graph);
        return tripsProcessor.findTripsWithRestriction(source, target, distance, TripsProcessor.RestrictionType.DISTANCE_MAXIMUM);
    }


}
