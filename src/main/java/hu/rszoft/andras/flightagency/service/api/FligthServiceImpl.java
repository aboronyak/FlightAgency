package hu.rszoft.andras.flightagency.service.api;

import hu.rszoft.andras.flightagency.dal.AirlineRepository;
import hu.rszoft.andras.flightagency.dal.FlightRepository;
import hu.rszoft.andras.flightagency.dal.entity.Airline;
import hu.rszoft.andras.flightagency.dal.entity.City;
import hu.rszoft.andras.flightagency.dal.entity.Flight;
import hu.rszoft.andras.flightagency.service.api.data.Itinerary;
import hu.rszoft.andras.flightagency.service.util.Dijkstra;
import hu.rszoft.andras.flightagency.service.util.Node;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FligthServiceImpl implements FlightService {

    private final AirlineRepository airlineRepository;
    private final CityService cityService;
    private final FlightRepository flightRepository;

    public FligthServiceImpl(AirlineRepository airlineRepository, FlightRepository flightRepository, CityService cityService) {
        this.airlineRepository = airlineRepository;
        this.cityService = cityService;
        this.flightRepository = flightRepository;
    }

    /**
     * Összes légitársaság lekérdezése
     *
     * @return AirLine lista
     */
    @Override
    public List<Airline> queryAirlines() {
        return this.airlineRepository.findAll();
    }

    @Override
    public Airline findAirline(String name) {
        return this.airlineRepository.findAirlineByName(name);
    }

    /**
     * Adott légitársaság összes járatának lekérdezése
     *
     * @param airline
     * @return Flight lista
     */
    @Override
    public List<Flight> queryFlights(Airline airline) {
        List<Flight> results;
        if (airline == null) {
            results = this.flightRepository.findAll();
        } else {
            results = this.flightRepository.findAllByAirline(airline);
        }
        return  results;
    }

    /**
     * Visszaadja egy adott városból egy másikba vezető összes járatot.
     * Ha valamelyik paraméter nincs kitöltve, az alapján nem szűrünk.
     *
     * @param from kiindulási város
     * @param to   érkezési város
     * @return járat lista
     */
    @Override
    public List<Flight> queryFlights(City from, City to) {
        return this.flightRepository.findAllByCityFromAndCityTo(from, to);
    }

    /**
     * Legrövidebb út számítás minden városhoz, kedvenc légitársaság figyelembe vételével
     * @param sourceCity kiindulási város
     * @param airline légitársaság
     * @return
     */
    @Override
    public Map<Integer, Node> getShortestPath(City sourceCity, Airline airline) {
        if (sourceCity == null) {
            throw new IllegalArgumentException("sourceCity param can't be nul!");
        }
        List<City> cities = this.cityService.queryCities();
        List<Flight> flights = this.queryFlights(null);
        Map<Integer, Node> cityNodeMap = new HashMap<>();

        //First create nodes
        for(City city : cities) {
            cityNodeMap.put(city.getId(), new Node(city));
        }

        for(Flight flight : flights) {
            City from = flight.getCityFrom();
            City to = flight.getCityTo();
            cityNodeMap.get(from.getId()).addDestination(cityNodeMap.get(to.getId()), flight);
        }

        Dijkstra.calculateShortestPathFromSource(cityNodeMap.get(sourceCity.getId()), airline);

        return cityNodeMap;
    }

    /**
     * Útvonalterv lekérdezése
     * @param sourceCity kiinduló város
     * @param destionationCity végállomás
     * @param airline kiválasztott légitársaság (lehet null)
     * @return
     */
    public Itinerary getItinerary(City sourceCity, City destionationCity, Airline airline) {
        if (sourceCity == null) {
            throw new IllegalArgumentException("sourceCity param can't be nul!");
        }
        if (destionationCity == null) {
            throw new IllegalArgumentException("sourceCity param can't be nul!");
        }

        return ItineraryFactory.buildItinerary(sourceCity, destionationCity, airline, getShortestPath(sourceCity,airline));
    }
}
