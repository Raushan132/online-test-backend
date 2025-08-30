package com.test.config;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import com.test.model.UserEntity;
import com.test.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepo;

    public CustomUserDetailsService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepo.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return User.builder()
                .username(user.getEmail())
                .password(user.getPassword()) // must be encoded
                .roles(user.getRole())
                .build();
    }
}

