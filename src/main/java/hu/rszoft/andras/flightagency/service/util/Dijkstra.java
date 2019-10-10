package hu.rszoft.andras.flightagency.service.util;

import hu.rszoft.andras.flightagency.dal.entity.Airline;
import hu.rszoft.andras.flightagency.dal.entity.Flight;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Legrövidebb út számítás Dijkstra algoritmus alapján
 * Gráf reprezentáció a node-okból kiolvasható
 */
public class Dijkstra {

    public static void calculateShortestPathFromSource(Node source, Airline preferredAirline) {

        source.setDistance(0L);

        Set<Node> settledNodes = new HashSet<>();
        Set<Node> unsettledNodes = new HashSet<>();
        unsettledNodes.add(source);

        while (unsettledNodes.size() != 0) {
            Node currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);
            //az aktuális node-ba vezető utakat keressük az összes szomszédból. Két pont között több út is lehet
            for (Entry<Node, List<Flight>> neighbourPair : currentNode.getNeighbourNodes().entrySet()) {
                Node neighbourNode = neighbourPair.getKey();
                List<Flight> flights = neighbourPair.getValue();

                if (!settledNodes.contains(neighbourNode)) {
                    flights.forEach(flight -> {
                        calculateMinimumDistance(neighbourNode, flight, currentNode, preferredAirline);
                    });
                    if (neighbourNode.getSelectedFlight() != null) {
                        unsettledNodes.add(neighbourNode);
                    }
                }
            }
            settledNodes.add(currentNode);
        }
    }

    private static void calculateMinimumDistance(Node evaluationNode, Flight flight, Node sourceNode, Airline preferredAirline) {
        Long sourceDistance = sourceNode.getDistance();
        boolean setCurrent = false;
        Long calculatedDistance = sourceDistance + flight.getDistance();
        if (preferredAirline == null) {
            setCurrent = calculatedDistance < evaluationNode.getDistance();
        } else if (flight.getAirline().getId() == preferredAirline.getId()) {
            setCurrent = calculatedDistance < evaluationNode.getDistance();
        }
        if (setCurrent) {
            evaluationNode.setDistance(sourceDistance + flight.getDistance());
            LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
            evaluationNode.setSelectedFlight(flight);
        }
    }

    private static Node getLowestDistanceNode(Set<Node> unsettledNodes) {
        Node lowestDistanceNode = null;
        Long lowestDistance = Long.MAX_VALUE;
        for (Node node : unsettledNodes) {
            Long nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }
}