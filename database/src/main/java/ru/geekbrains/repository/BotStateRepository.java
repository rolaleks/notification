package ru.geekbrains.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.entity.bot.BotData;

import java.util.Optional;

@Repository
public interface BotStateRepository extends JpaRepository<BotData, Long> {
    Optional<BotData> findByChatId(long id);
}
