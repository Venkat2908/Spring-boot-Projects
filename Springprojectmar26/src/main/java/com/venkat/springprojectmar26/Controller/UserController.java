package com.venkat.springprojectmar26.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.venkat.springprojectmar26.Dtos.*;
import com.venkat.springprojectmar26.Exception.ValidTokenNotFoundException;
import com.venkat.springprojectmar26.Models.Token;
import com.venkat.springprojectmar26.Models.User;
import com.venkat.springprojectmar26.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public TokenDto login (@RequestBody  LoginRequestDTO loginrequest){

        Token token = userService.login( loginrequest.getEmail(), loginrequest.getPassword());



        return TokenDto.fromToken(token);


    }



    @GetMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LogoutRequestDto logoutrequest){

        ResponseEntity<Void > responseentity = null;

        try {
            userService.logout(logoutrequest.getToken());

            responseentity = new ResponseEntity<>(HttpStatus.OK);
        }
        catch (ValidTokenNotFoundException e){
            responseentity = new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

        return responseentity;



    }
    @PostMapping("/Signup")
    public UserDto signup( @RequestBody SignupRequestDto signuprequest) throws JsonProcessingException {

        User user = userService.signup(signuprequest.getName(),
                signuprequest.getEmail(), signuprequest.getPassword());

        return UserDto.from(user);

    }

    @GetMapping ("/validate/{token}")
    public UserDto ValidateToken(@PathVariable String token){

      try{
          User user = userService.validateToken(token);
          return UserDto.from(user);
      }
      catch(ValidTokenNotFoundException e) {
          throw new RuntimeException(e);
        }

    }

}
