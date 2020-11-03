package ru.geekbrains.service;


import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.geekbrains.config.SecurityUserImplements;
import ru.geekbrains.entity.user.User;
import ru.geekbrains.repository.UserRepository;


@Service
@AllArgsConstructor
public class UserDetailsServiceImplements implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(phone).orElseThrow(() ->
                new UsernameNotFoundException("User doesn't exists"));
        return SecurityUserImplements.createUser(user);
    }
}
