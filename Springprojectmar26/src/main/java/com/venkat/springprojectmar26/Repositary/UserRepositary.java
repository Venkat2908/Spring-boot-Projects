package com.venkat.springprojectmar26.Repositary;

import com.venkat.springprojectmar26.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepositary extends JpaRepository<User,Long> {
    User save(User user);

    Optional<User> findByEmail(String email);
    Optional<User> findByName(String name);



}
