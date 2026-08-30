package com.venkat.springprojectmar26.Security.Service;

import com.venkat.springprojectmar26.Models.User;
import com.venkat.springprojectmar26.Repositary.UserRepositary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component

public class CustomUserDetailsService implements UserDetailsService {

    private UserRepositary userRepositary;

    public CustomUserDetailsService(UserRepositary userRepositary) {
        this.userRepositary = userRepositary;

    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> optionaluser = userRepositary.findByEmail(username);
        if (optionaluser.isEmpty()) {
            throw new UsernameNotFoundException("userName" +username+ " not found");

        }

        User user = optionaluser.get();

        return null;
    }


}
