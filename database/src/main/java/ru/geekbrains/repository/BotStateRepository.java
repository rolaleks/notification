package ru.geekbrains.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.entity.BotStateData;

import java.util.Optional;

@Repository
public interface BotStateRepository extends JpaRepository<BotStateData, Long> {
    Optional<BotStateData> findByChatId(long id);
}
