package trains;

import java.util.List;

public class TripData {
    private int distance;
    private List<String> trip;

    TripData(int distance, List<String> trip) {
        this.distance = distance;
        this.trip = trip;
    }

    public int getDistance() {
        return distance;
    }

    public List<String> getTrip() {
        return trip;
    }
}
