package com.venkat.springprojectmar26.Dtos;

import com.venkat.springprojectmar26.Models.Token;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogoutRequestDto {
    private String token;
}
