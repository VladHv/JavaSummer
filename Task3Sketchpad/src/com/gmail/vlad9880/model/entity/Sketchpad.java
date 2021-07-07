package com.gmail.vlad9880.model.entity;

public class Sketchpad {

    private String firstName;
    private String login;

    public Sketchpad(String firstName, String login) throws NotUniqueLoginException{
        this.firstName = firstName;
        if (!DBSketchpad.checkLogin(login)) {
            this.login = login;
        } else {
            throw new NotUniqueLoginException("Not unique login", login);
        }
    }

    @Override
    public String toString() {
        return "Sketchpad{" +
                "firstName='" + firstName + '\'' +
                ", login='" + login + '\'' +
                '}';
    }
}
