package ru.geekbrains.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdRepository extends JpaRepository<Ad, Long> {


    Optional<Ad> findByLink(String link);
}
