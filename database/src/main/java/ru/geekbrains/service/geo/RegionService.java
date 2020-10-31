package ru.geekbrains.service.geo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.entity.Country;
import ru.geekbrains.entity.Region;
import ru.geekbrains.repository.RegionRepository;

import java.util.Optional;

@Service
public class RegionService{

    private final RegionRepository repository;


    @Autowired
    public RegionService(RegionRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void save(@NonNull Region region) {
        repository.save(region);
    }

    @Transactional(readOnly = true)
    public Optional<Region> findById(long id) {
        return repository.findById(id);
    }

    @Transactional
    public Region findOrCreate(@NonNull String name, @NonNull Country country) {

        Optional<Region> regionOptional = repository.findByNameContainingIgnoreCaseAndCountry(name, country);

        if (regionOptional.isPresent()) {
            return regionOptional.get();
        }

        Region region = new Region();
        region.setCountry(country);
        region.setName(name);
        this.save(region);

        return region;
    }
}

