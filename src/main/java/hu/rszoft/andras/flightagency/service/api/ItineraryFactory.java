package hu.rszoft.andras.flightagency.service.api;

import hu.rszoft.andras.flightagency.dal.entity.Airline;
import hu.rszoft.andras.flightagency.dal.entity.City;
import hu.rszoft.andras.flightagency.service.api.data.Itinerary;
import hu.rszoft.andras.flightagency.service.util.Node;

import java.util.LinkedList;
import java.util.Map;

public class ItineraryFactory {

    public static Itinerary buildItinerary(City sourceCity, City targetCity, Airline airline, Map<Integer, Node> shortestPaths) {
        Itinerary result = new Itinerary();
        result.setAirline(airline);
        Node destinationNode = shortestPaths.get(targetCity.getId());
        LinkedList<Node> shortestPath = destinationNode.getShortestPath();
        if (shortestPath != null) {
            shortestPath.forEach(node1 -> {
                if (node1 != null) {
                    if (node1.getSelectedFlight() != null) {
                        result.addTransfer(node1.getSelectedFlight());
                        if (airline != null && node1.getSelectedFlight().getAirline().getId() != airline.getId()) {
                            result.setHasOtherAirlineTransfer(true);
                        }
                    }
                }
            });
        }
        if (destinationNode.getSelectedFlight() != null) {
            result.addTransfer(destinationNode.getSelectedFlight());
            if (airline != null && destinationNode.getSelectedFlight().getAirline().getId() != airline.getId()) {
                result.setHasOtherAirlineTransfer(true);
            }
        }
        return result;
    }
}
