package hu.rszoft.andras.flightagency.service.api;

import hu.rszoft.andras.flightagency.dal.entity.City;

import java.util.List;

public interface CityService {

    City findSmallest();

    City findLargest();

    List<City> queryCities();

    City findByName(String city);
}
