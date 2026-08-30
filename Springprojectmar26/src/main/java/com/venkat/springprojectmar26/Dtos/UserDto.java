package com.venkat.springprojectmar26.Dtos;

import com.venkat.springprojectmar26.Models.Role;
import com.venkat.springprojectmar26.Models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDto {
    private String username;
    private String email;
    private List<Role> roles;

    public static UserDto from(User user) {
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getName());
        userDto.setEmail(user.getEmail());
        return userDto;

    }
}
