package ru.geekbrains.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.entity.City;
import ru.geekbrains.entity.District;
import ru.geekbrains.entity.Street;

import java.util.List;

@Repository
public interface StreetRepository extends JpaRepository<Street, Long> {


    List<Street> findByNameContainingIgnoreCaseAndCity(String name, City city);

    List<Street> findByNameContainingIgnoreCaseAndDistrict(String name, District district);
}
