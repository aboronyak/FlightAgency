package hu.rszoft.andras.flightagency;

import hu.rszoft.andras.flightagency.dal.entity.Airline;
import hu.rszoft.andras.flightagency.dal.entity.City;
import hu.rszoft.andras.flightagency.service.api.CityService;
import hu.rszoft.andras.flightagency.service.api.FlightService;
import hu.rszoft.andras.flightagency.service.api.data.Itinerary;
import hu.rszoft.andras.flightagency.service.util.Node;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Component
public class FlightAgencyRunner implements CommandLineRunner {

    private final FlightService flightService;
    private final CityService cityService;

    public FlightAgencyRunner(FlightService flightService, CityService cityService) {
        this.flightService = flightService;
        this.cityService = cityService;
    }

    @Override
    public void run(String... args) throws Exception {
        City smallest = this.cityService.findSmallest();
        System.out.printf("Legkisebb város:  %-10s, %s lakos%n",smallest.getName(), smallest.getPopulation());
        City largest = this.cityService.findLargest();
        System.out.printf("Legnagyobb város: %-10s, %s lakos%n",largest.getName(), largest.getPopulation());

        System.out.printf("Legrövidebb út:%n");
        List<Airline> airlines = this.flightService.queryAirlines();
        airlines.stream().forEach(airline -> {
            //legrövidebb utak lekérdezése
           Itinerary itinerary = this.flightService.getItinerary(smallest, largest, airline);
           System.out.printf("%s%n", itinerary);
        });
        Itinerary itinerary = this.flightService.getItinerary(smallest, largest, null);
        System.out.printf("%s%n", itinerary);
    }
}
