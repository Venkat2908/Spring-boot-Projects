package com.venkat.springprojectmar26.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.venkat.springprojectmar26.Exception.ValidTokenNotFoundException;
import com.venkat.springprojectmar26.Models.Token;
import com.venkat.springprojectmar26.Models.User;

public interface UserService {

   Token login (String email, String password );

   User signup (String name , String email, String password ) throws JsonProcessingException;

   void logout(String token) throws ValidTokenNotFoundException;

   User validateToken (String token) throws ValidTokenNotFoundException;
}
