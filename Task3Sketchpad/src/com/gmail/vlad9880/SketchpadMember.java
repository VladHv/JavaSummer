package com.gmail.vlad9880;

/**
 * Created by Vlad Herasimov on 03.07.2021
 */

public class SketchpadMember {

    private String name;
    private String surname;
    private String nickname;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "Member [" +
                "name: " + name +
                ", surname: " + surname +
                ", nickname: " + nickname +
                "]";
    }

}
