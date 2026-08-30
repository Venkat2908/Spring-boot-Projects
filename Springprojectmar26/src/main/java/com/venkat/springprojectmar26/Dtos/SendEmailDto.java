package com.venkat.springprojectmar26.Dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendEmailDto {

    private String to;
    private String subject;
    private String body;
}
