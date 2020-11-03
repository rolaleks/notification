package ru.geekbrains.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.entity.City;
import ru.geekbrains.entity.Country;
import ru.geekbrains.entity.Region;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    List<City> findByNameContainingIgnoreCaseAndCountry(String name, Country country);

    List<City> findByNameContainingIgnoreCaseAndRegion(String name, Region region);
}
