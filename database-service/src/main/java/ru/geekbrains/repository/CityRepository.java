package ru.geekbrains.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.entities.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
}
