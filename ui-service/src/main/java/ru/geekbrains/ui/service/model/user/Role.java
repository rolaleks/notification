package ru.geekbrains.ui.service.model.user;

import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


@Getter
public enum Role {
    USER("READ"),
    ADMIN("READ", "WRITE");

    private Set<SimpleGrantedAuthority> grantedAuthorities = new HashSet<>();

    Role(String... s) {
        Arrays.stream(s).forEach(a -> {
            switch (a) {
                case "READ":
                    grantedAuthorities.add(new SimpleGrantedAuthority("READ"));
                    break;
                case "WRITE":
                    grantedAuthorities.add(new SimpleGrantedAuthority("WRITE"));
                    break;
                default:
                    break;
            }
        });
    }
}
