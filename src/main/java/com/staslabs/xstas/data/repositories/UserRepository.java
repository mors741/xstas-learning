package com.staslabs.xstas.data.repositories;

import com.staslabs.xstas.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User getByEmail(String email);
}
