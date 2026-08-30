package com.venkat.backendproject.Advice;


import com.venkat.backendproject.DTOS.ErrorDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controlleradvice {
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorDTO> handleNullPointerException(NullPointerException e) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setMessage("something went wrong please try again");

    return new ResponseEntity<>(errorDTO,HttpStatusCode.valueOf(404));

    }


}
