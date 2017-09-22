package com.staslabs.xstas.config;

import com.staslabs.xstas.data.repositories.UserRepository;
import com.staslabs.xstas.data.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.getByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("No user present with email: " + email);
        } else {
            String role = user.getRole().toString();
            Collection<GrantedAuthority> rolesCollection = Collections.singleton(new SimpleGrantedAuthority(role));
            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), rolesCollection);
        }
    }
}
