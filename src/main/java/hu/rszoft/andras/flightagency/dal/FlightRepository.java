package hu.rszoft.andras.flightagency.dal;

import hu.rszoft.andras.flightagency.dal.entity.Airline;
import hu.rszoft.andras.flightagency.dal.entity.City;
import hu.rszoft.andras.flightagency.dal.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long>, JpaSpecificationExecutor<Flight> {

    List<Flight> findAllByAirline(Airline airline);

    @Query("SELECT c FROM Flight c WHERE (:cityFrom is null or c.cityFrom = :cityFrom) and (:cityTo is null"
            + " or c.cityTo = :cityTo)")
    List<Flight> findAllByCityFromAndCityTo(@Param("cityFrom") City cityFrom, @Param("cityTo") City cityTo);
}