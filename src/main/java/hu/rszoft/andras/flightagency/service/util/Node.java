package hu.rszoft.andras.flightagency.service.util;

import hu.rszoft.andras.flightagency.dal.entity.City;
import hu.rszoft.andras.flightagency.dal.entity.Flight;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class Node {

    private City city;

    private LinkedList<Node> shortestPath;

    private Long distance;

    private Map<Node, List<Flight>> neighbourNodes;

    private Flight selectedFlight;

    public Node(City city) {
        this.city = city;
        this.shortestPath = new LinkedList<>();
        this.distance = Long.MAX_VALUE;
        this.neighbourNodes = new HashMap<>();
    }

    public void addDestination(Node destination, Flight flight) {
        List<Flight> flights = this.neighbourNodes.get(destination);
        if (flights == null) {
            flights = new LinkedList<>();
            this.neighbourNodes.put(destination, flights);
        }
        flights.add(flight);
    }
}