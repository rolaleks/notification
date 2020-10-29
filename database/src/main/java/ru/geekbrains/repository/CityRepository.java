package ru.geekbrains.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.entity.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
}
