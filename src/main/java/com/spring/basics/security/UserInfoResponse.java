package com.spring.basics.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoResponse
{
    private int empId;
    private String refreshToken;
    private String name;
    private String password;
    private List<String> roles;
}