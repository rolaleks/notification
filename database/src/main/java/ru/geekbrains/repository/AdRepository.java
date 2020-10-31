package ru.geekbrains.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.entity.Ad;

import java.util.Optional;

@Repository
public interface AdRepository extends JpaRepository<Ad, Long> {


    Optional<Ad> findByLink(String link);
}