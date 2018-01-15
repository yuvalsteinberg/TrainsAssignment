package trains;

import java.util.*;

class Graph {
    // Map of all direct graph routes from a source to all its destinations, and the distance per destination
    private Map<String, Map<String, Integer>> routes;

    Graph() {
        routes = new HashMap<>();
    }

    // Build the graph by parsing the original graph and building the entire information of all possible routes
    public void build(String graphStr) {
        clear();
        buildRoutes(graphStr);
    }

    // Return the direct distance from source to distination.
    // If no such exists, null is returned
    public Integer getDistance(String source, String destination) {
        if (!routes.containsKey(source)) {
            return null;
        }

        return routes.get(source).get(destination);
    }

    // Return all the destination stations of the given source.
    // And empty list is returned if no data for this source
    public List<String> getAllDestinations(String source) {
        Map<String, Integer> destinations = routes.get(source);
        if (destinations == null) {
            destinations = new HashMap<>();
        }

        return new ArrayList<>(destinations.keySet());
    }

    private void clear() {
        routes = new HashMap<>();
    }

    private void buildRoutes(String graphStr) {
        List<String> allDirectRouteStrings = Arrays.asList(graphStr.trim().split(","));

        allDirectRouteStrings
                .stream()
                .map(this::createRoute)
                .forEach(this::addRouteToAllDirectRoutes);
    }

    private Route createRoute(String routeStr) {
        routeStr = routeStr.trim();
        if (routeStr.length() < 3) {
            throw new IllegalArgumentException("Route is illegal: " + routeStr);
        }

        return new Route(
                Character.toString(routeStr.charAt(0)),
                Character.toString(routeStr.charAt(1)),
                Integer.parseInt(routeStr.substring(2))
        );
    }

    private void addRouteToAllDirectRoutes(Route route) {
        String source = route.getSource();
        if (!routes.containsKey(source)) {
            routes.put(source, new HashMap<>());
        }

        routes.get(source).put(route.getDestination(), route.getDistance());
    }

}
