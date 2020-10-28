package ru.geekbrains.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    public void save(Country country) {
        repository.save(country);
    }

    @Transactional(readOnly = true)
    public Optional<Country> findById(long id) {
        return repository.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<Country> findByName(String name) {
        return repository.findByName(name);
    }
}
