package hu.rszoft.andras.flightagency;

import hu.rszoft.andras.flightagency.dal.entity.Airline;
import hu.rszoft.andras.flightagency.dal.entity.City;
import hu.rszoft.andras.flightagency.dal.entity.Flight;
import hu.rszoft.andras.flightagency.service.api.CityService;
import hu.rszoft.andras.flightagency.service.api.FlightService;
import hu.rszoft.andras.flightagency.service.util.Node;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("flightAPITestProfile")
public class FlightAgencyApplicationTests {

	@Autowired
	private FlightService fligthService;
	@Autowired
	private CityService cityService;

	@Test
	public void contextLoads() {
	}

	@Before
	public void setup() {
	}

	/*
	*	Függyvény teszt, összes légitársaság lekérdezése
	 */
	@Test
	public void airLinesTest() {
		List<Airline> airlines = this.fligthService.queryAirlines();
		assertThat(airlines).isNotNull();
	}

	@Test
	public void queryAllFlightTest() {
		List<Flight> flights = this.fligthService.queryFlights(null);
		assertThat(flights).isNotNull();
	}

	@Test
	@Transactional
	public void queryFlightByCityTest() {
		List<City> cities = this.cityService.queryCities();
		assertThat(cities).isNotNull();
	}

	@Test
	public void queryMinMaxCityTest() {
		City smallest = this.cityService.findSmallest();
		City largest = this.cityService.findLargest();
		assertThat(smallest).isNotNull();
		assertThat(largest).isNotNull();
	}
}
