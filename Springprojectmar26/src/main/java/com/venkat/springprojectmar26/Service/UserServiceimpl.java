package com.venkat.springprojectmar26.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.venkat.springprojectmar26.Dtos.SendEmailDto;
import com.venkat.springprojectmar26.Exception.ValidTokenNotFoundException;
import com.venkat.springprojectmar26.Models.Token;
import com.venkat.springprojectmar26.Models.User;
import com.venkat.springprojectmar26.Repositary.TokenRepositary;
import com.venkat.springprojectmar26.Repositary.UserRepositary;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;


@Service
public class UserServiceimpl implements UserService {

    private UserRepositary userRepositary;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private TokenRepositary tokenRepositary;
    private KafkaTemplate<String, String> kafkaTemplate;
    private ObjectMapper objectMapper;

    public UserServiceimpl(UserRepositary userRepositary,
                           BCryptPasswordEncoder bCryptPasswordEncoder,TokenRepositary tokenRepositary,
                           KafkaTemplate kafkaTemplate, ObjectMapper objectMapper) {
        this.userRepositary = userRepositary;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokenRepositary = tokenRepositary;
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;


    }



    @Override
    public Token login(String email, String password) {


        Optional<User> optionaluser = userRepositary.findByEmail(email);

        if (optionaluser.isEmpty()) {
            return null;

        }
        User user = optionaluser.get();

        Token token = null;
        if (bCryptPasswordEncoder.matches(password, user.getPassword())) {

            token = new Token();
            token.setUser(user);

            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH , 30);
            Date date = calendar.getTime();

            token.setExpiryAt(date);

            // token value can be any random string of 128 characters

           token.setValue(RandomStringUtils.randomAlphanumeric(128));



        }


        return tokenRepositary.save(token) ;
    }

    @Override
    public User signup(String name, String email, String password) throws JsonProcessingException {

        Optional<User> optionalUser = userRepositary.findByName(name);

        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }

        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setIsVerified(true);

        SendEmailDto sendmail  = new SendEmailDto();
        sendmail.setTo(email);
        sendmail.setSubject("Sign up invitation");
        sendmail.setBody("welcome to scalar academy");

//publish an event
        kafkaTemplate.send("signup", objectMapper.writeValueAsString(sendmail));



       return userRepositary.save(user);

    }

    @Override
    public void logout(String token) throws ValidTokenNotFoundException {
        Optional<Token> optionaltoken = tokenRepositary.findByValueAndDeletedAndExpiryAtGreaterThan(token,
                false,new Date());

        if (optionaltoken.isEmpty()) {
            throw new ValidTokenNotFoundException("Invalid token");
        }

        Token tokenvalue = optionaltoken.get();
        tokenvalue.setDeleted(true);
        tokenRepositary.save(tokenvalue);


    }

    @Override
    public User validateToken(String token) throws ValidTokenNotFoundException {
        Optional<Token> optional = tokenRepositary.findByValueAndDeletedAndExpiryAtGreaterThan(token,
                false,new Date());

        if (optional.isEmpty()) {
            throw new ValidTokenNotFoundException("Invalid user");
        }

        Token tokenvalue = optional.get();
        return tokenvalue.getUser();



    }
}
