package com.nikhil.pricetracker.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserDto {
    private String name;
    private String email;
    private String password;
}
