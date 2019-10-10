package hu.rszoft.andras.flightagency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FlightAgencyApplication {

	public static void main(String[] args) {
		System.out.println("FlightAgency application STARTED!");
		System.out.println("---------------------------------");
		SpringApplication.run(FlightAgencyApplication.class, args);
		System.out.println("---------------------------------");
		System.out.println("FlightAgency application ENDED!");
	}
}