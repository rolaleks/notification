package ru.geekbrains.service.geo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.entity.City;
import ru.geekbrains.entity.Country;
import ru.geekbrains.repository.CountryRepository;

import java.util.Optional;

@Service
public class CountryService {

    private final CountryRepository repository;


    @Autowired
    public CountryService(CountryRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void save(@NonNull Country country) {
        repository.save(country);
    }

    @Transactional(readOnly = true)
    public Optional<Country> findById(long id) {
        return repository.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<Country> findByName(@NonNull String name) {
        return repository.findByName(name);
    }

    public Country findOrCreate(@NonNull String name) {

        Optional<Country> cityOptional = repository.findByNameContainingIgnoreCase(name);

        if (cityOptional.isPresent()) {
            return cityOptional.get();
        }

        Country country = new Country();
        country.setName(name);
        this.save(country);

        return country;
    }
}

