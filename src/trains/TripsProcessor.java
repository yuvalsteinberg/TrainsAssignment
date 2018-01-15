package trains;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class TripsProcessor {
    public enum RestrictionType {
        STOPS_MAXIMUM,
        STOPS_EXACT,
        SHORTEST_TRIP,
        DISTANCE_MAXIMUM
    }

    // The routes graph
    private Graph graph;

    TripsProcessor(Graph graph) {
        this.graph = graph;
    }

    // Find all the possible trips from source to target that follows the given restriction
    public List<TripData> findTripsWithRestriction(
            String source,
            String target,
            int requiredRestriction,
            RestrictionType restrictionType) {

        List<TripData> foundTrips = new ArrayList<>();
        dfsStationsWithRestrictionRecursive(
                target,
                Collections.singletonList(source),
                0,
                requiredRestriction,
                restrictionType,
                foundTrips
        );

        return foundTrips;
    }

    // DFS from the last station (last of `tripFromSource` to target
    // while updating `foundTrips` with trips that follows the given restriction
    private void dfsStationsWithRestrictionRecursive(String target,
                                                     List<String> tripFromSource,
                                                     int distanceFromSource,
                                                     int requiredRestriction,
                                                     RestrictionType restrictionType,
                                                     List<TripData> foundTrips) {

        String previousStation = tripFromSource.get(tripFromSource.size() - 1);
        graph.getAllDestinations(previousStation)
                .forEach(nextStations -> dfsNextStationWithRestriction(
                        target,
                        previousStation,
                        nextStations,
                        tripFromSource,
                        distanceFromSource,
                        requiredRestriction,
                        restrictionType,
                        foundTrips
                        )
                );
    }

    // Check if the route from previous to next station is relevant for the trip or continuation of the DFS.
    // If relevant for the trip - add it
    // If need to continue - continue the DFS
    private void dfsNextStationWithRestriction(String target,
                                               String previousStation,
                                               String nextStation,
                                               List<String> tripFromSource,
                                               int distanceFromSource,
                                               int requiredRestriction,
                                               RestrictionType restrictionType,
                                               List<TripData> foundTrips) {
        if ((restrictionType == RestrictionType.SHORTEST_TRIP) && shouldNotProcessOnShortestTrip(nextStation, tripFromSource)) {
            return;
        }

        List<String> tripFromSourceToNewDestination = new ArrayList<>(tripFromSource);
        tripFromSourceToNewDestination.add(nextStation);
        distanceFromSource += graph.getDistance(previousStation, nextStation);

        // Check if a trip is found within the required restrictions and add it if such
        int newNumStops = tripFromSourceToNewDestination.size() - 1;
        boolean reachedTargetStation = nextStation.equals(target);
        if (reachedTargetStation) {
            boolean shouldAdd = false;
            switch (restrictionType) {
                case STOPS_MAXIMUM:
                    shouldAdd = newNumStops <= requiredRestriction;
                    break;

                case STOPS_EXACT:
                    shouldAdd = newNumStops == requiredRestriction;
                    break;

                case SHORTEST_TRIP:
                    shouldAdd = true;
                    break;

                case DISTANCE_MAXIMUM:
                    shouldAdd = distanceFromSource < requiredRestriction;
                    break;
            }

            if (shouldAdd) {
                foundTrips.add(new TripData(distanceFromSource, tripFromSourceToNewDestination));
            }
        }

        // Continue DFS if required
        boolean shouldContinue = true;
        switch (restrictionType) {
            case STOPS_MAXIMUM:
            case STOPS_EXACT:
                shouldContinue = newNumStops < requiredRestriction;
                break;

            case SHORTEST_TRIP:
                shouldContinue = !reachedTargetStation;
                break;

            case DISTANCE_MAXIMUM:
                shouldContinue = distanceFromSource < requiredRestriction;
                break;
        }

        if (shouldContinue) {
            dfsStationsWithRestrictionRecursive(
                    target,
                    tripFromSourceToNewDestination,
                    distanceFromSource,
                    requiredRestriction,
                    restrictionType,
                    foundTrips
            );
        }
    }

    // Check if should not process the station (node) in case of shortest trip
    private boolean shouldNotProcessOnShortestTrip(String nextStation, List<String> tripFromSource) {
        int size = tripFromSource.size();
        return (size > 1) && tripFromSource.subList(1, tripFromSource.size() - 1).contains(nextStation);
    }

}
