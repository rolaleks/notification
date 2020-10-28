package ru.geekbrains.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.geekbrains.dto.RegisterUserDto;
import ru.geekbrains.entity.user.Role;
import ru.geekbrains.entity.user.Status;
import ru.geekbrains.entity.user.User;
import ru.geekbrains.repository.RoleRepository;
import ru.geekbrains.repository.UserRepository;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public void save(RegisterUserDto registerUserDto) {
        User userNew = new User();
        userNew.setLogin(registerUserDto.getLogin());
        userNew.setName(registerUserDto.getName());
        userNew.setPassword(passwordEncoder.encode(registerUserDto.getPassword()));
        userNew.setStatus(Status.ACTIVE);
        Optional<Role> byId = roleRepository.findById(2l);
        List<Role> list = new ArrayList();
        list.add(byId.get());
        userNew.setRoles(list);
        userRepository.save(userNew);
    }
}
