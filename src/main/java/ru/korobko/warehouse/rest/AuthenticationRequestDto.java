package ru.korobko.warehouse.rest;

import lombok.Data;

@Data
public class AuthenticationRequestDto {
    private String username;
    private String password;
}