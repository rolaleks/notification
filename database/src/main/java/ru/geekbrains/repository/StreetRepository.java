package ru.geekbrains.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.entity.Street;

@Repository
public interface StreetRepository extends JpaRepository<Street, Long> {
}