package com.spring.basics.entry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Register
{
    private String name;
    private String mail;
    private String password;
    private List<String> roles;
}
