package ru.geekbrains.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.entities.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {
}
