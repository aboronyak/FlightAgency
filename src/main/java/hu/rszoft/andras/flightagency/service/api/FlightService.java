package hu.rszoft.andras.flightagency.service.api;

import hu.rszoft.andras.flightagency.dal.entity.Airline;
import hu.rszoft.andras.flightagency.dal.entity.City;
import hu.rszoft.andras.flightagency.dal.entity.Flight;
import hu.rszoft.andras.flightagency.service.api.data.Itinerary;
import hu.rszoft.andras.flightagency.service.util.Node;

import java.util.List;
import java.util.Map;

/**
 * Flight API specification
 * @author Andras
 *
 */
public interface FlightService {

    List<Airline> queryAirlines();

    Airline findAirline(String name);

    List<Flight> queryFlights(Airline airline);

    List<Flight> queryFlights(City from, City to);

    Map<Integer, Node> getShortestPath(City sourceCity, Airline airline);

    Itinerary getItinerary(City sourceCity, City destionationCity, Airline airline);
}