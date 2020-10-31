package ru.geekbrains.service.geo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.entity.*;
import ru.geekbrains.repository.StreetRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StreetService {

    private final StreetRepository repository;


    @Autowired
    public StreetService(StreetRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void save(@NonNull Street street) {
        repository.save(street);
    }

    @Transactional(readOnly = true)
    public Optional<Street> findById(long id) {
        return repository.findById(id);
    }


    @Transactional
    public Optional<Street> findInCity(@NonNull String name, @NonNull City city) {

        List<Street> streets = repository.findByNameContainingIgnoreCaseAndCity(name, city);
        return Optional.ofNullable(streets.size() > 0 ? streets.get(0) : null);
    }

    @Transactional
    public Street findOrCreate(@NonNull String name, @NonNull City city, District district) {

        if (district != null) {
            List<Street> streets = repository.findByNameContainingIgnoreCaseAndDistrict(name, district);

            if (streets.size() > 0){
                return streets.get(0);
            }
        } else {
            Optional<Street> street = this.findInCity(name, city);
            if (street.isPresent()) {
                return street.get();
            }
        }


        Street street = new Street();
        street.setDistrict(district);
        street.setCity(city);
        street.setName(name);
        this.save(street);

        return street;
    }
}

