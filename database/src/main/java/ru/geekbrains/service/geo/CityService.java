package ru.geekbrains.service.geo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.entity.City;
import ru.geekbrains.entity.Country;
import ru.geekbrains.entity.Region;
import ru.geekbrains.repository.CityRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {

    private final CityRepository repository;


    @Autowired
    public CityService(CityRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void save(@NonNull City city) {
        repository.save(city);
    }

    @Transactional(readOnly = true)
    public Optional<City> findById(long id) {
        return repository.findById(id);
    }

    @Transactional
    public Optional<City> findInCountry(@NonNull String name, @NonNull Country country) {

        List<City> cities = repository.findByNameContainingIgnoreCaseAndCountry(name, country);
        return Optional.ofNullable(cities.size() > 0 ? cities.get(0) : null);
    }

    @Transactional
    public City findOrCreate(@NonNull String name, @NonNull Country country, Region region) {

        if (region != null) {
            List<City> cities = repository.findByNameContainingIgnoreCaseAndRegion(name, region);

            if (cities.size() > 0) {
                return cities.get(0);
            }
        } else {
            Optional<City> city = this.findInCountry(name, country);
            if (city.isPresent()) {
                return city.get();
            }
        }

        City city = new City();
        city.setRegion(region);
        city.setCountry(country);
        city.setName(name);
        this.save(city);

        return city;
    }
}

