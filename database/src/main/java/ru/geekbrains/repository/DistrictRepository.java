package ru.geekbrains.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.entity.District;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {
}