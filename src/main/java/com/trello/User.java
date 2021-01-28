package com.trello;

import lombok.Getter;

@Getter
public class User {
    private String userLogin;
    private String userPassword;

    public User(String login, String password) {
        userLogin = login;
        userPassword = password;
    }
}
