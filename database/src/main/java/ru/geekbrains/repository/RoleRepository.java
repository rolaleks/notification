package ru.geekbrains.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.entity.user.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
