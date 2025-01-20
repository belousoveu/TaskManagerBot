package com.github.belousovea.telegrambotcore.models;

import lombok.Getter;

@Getter
public abstract class AbstractUser {
    private long id;
    private String firstName;
    private String lastName;
    private int timeZoneOffset;
    private String username;
}
