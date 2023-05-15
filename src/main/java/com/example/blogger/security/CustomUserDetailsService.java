package com.example.blogger.security;

import com.example.blogger.model.User;
import com.example.blogger.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);

        if(user != null) {
            return new org.springframework.security.core.userdetails.User(
                    user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRole())
            );
        }
        else {
            throw new UsernameNotFoundException("Invalid credentials");
        }
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(String role) {
        Collection<String> roles = new ArrayList<>();
        roles.add(role);
        Collection<? extends GrantedAuthority> mapRoles = roles.stream()
                .map(r -> new SimpleGrantedAuthority(r))
                .collect(Collectors.toList());
        return mapRoles;
    }
}
