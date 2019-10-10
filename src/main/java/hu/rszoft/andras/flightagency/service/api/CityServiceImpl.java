package hu.rszoft.andras.flightagency.service.api;

import hu.rszoft.andras.flightagency.dal.CityRepository;
import hu.rszoft.andras.flightagency.dal.entity.City;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    /**
     * Legkisebb város lekérdezése
     * @return
     */
    @Override
    public City findSmallest() {
        //A feladat kiírásból nem volt egyértelmű, hogy a DB műveletek kerülése-e a cél (jelen esetben a DB is memóriában van).
        //Emiatt ebben az esetben SQL helyett java oldali kiválasztást válaszottam
        return Collections.min(cityRepository.findAll(), Comparator.comparing(City::getPopulation));
    }

    /**
     * Legnagyobb város lekérdezése
     * @return
     */
    @Override
    public City findLargest() {
        //A feladat kiírásból nem volt egyértelmű, hogy a DB műveletek kerülése-e a cél (jelen esetben a DB is memóriában van).
        //Emiatt ebben az esetben SQL helyett java oldali kiválasztást válaszottam
        return Collections.max(cityRepository.findAll(), Comparator.comparing(City::getPopulation));
    }

    /**
     * Összes város lekérdezése
     * @return
     */
    @Override
    public List<City> queryCities() {
        return this.cityRepository.findAll();
    }

    /**
     * Város lekérdezése név alapján
     * @return
     */
    @Override
    public City findByName(String city) {
        return this.cityRepository.findByName(city);
    }
}
