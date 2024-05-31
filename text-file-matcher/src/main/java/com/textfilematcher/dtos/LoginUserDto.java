package com.textfilematcher.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class LoginUserDto {

    @JsonProperty("user_name")
    private String userName;

    private String email;
    
    private String password;
}
