package com.venkat.springprojectmar26.Repositary;

import com.venkat.springprojectmar26.Models.Token;
import com.venkat.springprojectmar26.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

public interface TokenRepositary extends JpaRepository<Token,Long> {

    Optional<Token> findByValue(String value);
    Token findByValueAndUser(String value, User user);

    Optional<Token> findByValueAndDeletedAndExpiryAtGreaterThan(String value, boolean deleted, Date expriyAt);

    Token save(Token token);


}
