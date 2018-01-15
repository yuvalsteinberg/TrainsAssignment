package trains;

class Route {
    private String source;
    private String destination;
    private int distance;

    Route(String source, String destination, int distance) {
        this.source = source;
        this.destination = destination;
        this.distance = distance;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public int getDistance() {
        return distance;
    }
}
