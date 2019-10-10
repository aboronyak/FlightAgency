package hu.rszoft.andras.flightagency.dal;

import hu.rszoft.andras.flightagency.dal.entity.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AirlineRepository extends JpaRepository<Airline, Long>, JpaSpecificationExecutor<Airline> {

    Airline findAirlineByName(String name);
}