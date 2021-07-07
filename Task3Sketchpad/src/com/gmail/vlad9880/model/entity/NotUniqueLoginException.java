package com.gmail.vlad9880.model.entity;

public class NotUniqueLoginException extends Exception {

    private String login;

    public String getLogin() {
        return login;
    }

    public NotUniqueLoginException(String message, String login) {
        super(message);
        this.login = login;

    }
}
