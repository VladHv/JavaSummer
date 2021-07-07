package com.gmail.vlad9880.model.entity;

/**
 * Created by Vlad Herasimov on 07.07.2021
 */

public enum DBSketchpad {
    NOTE_ONE("Vlad", "vlad9880"),
    NOTE_TWO("Alex", "sanya228"),
    NOTE_THREE("John", "johnny_13");

    private final String firstName;
    private final String login;

    DBSketchpad(String firstName, String login) {
        this.firstName = firstName;
        this.login = login;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLogin() {
        return login;
    }

    public static boolean checkLogin (String login) {
        for (DBSketchpad record: DBSketchpad.values()) {
            if (record.getLogin().equals(login)) {
                return true;
            }
        }
        return false;
    }
}
