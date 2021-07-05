package com.gmail.vlad9880;

/**
 * Created by Vlad Herasimov on 03.07.2021
 */

public class Record {

    private String name;
    private String surname;
    private String shortName;
    private String nickname;
    private Group group;
    private String email;

    //Tested
    public void generateShortName() {
        shortName = surname.concat(" " + name.charAt(0) + ".");
    }

    //Tested
    public void setGroup(String group) {
        if (group.equals("W")) {
            this.group = Group.WORK;
        } else if (group.equals("F")) {
            this.group = Group.FAMILY;
        } else if (group.equals("M")) {
            this.group = Group.MATES;
        } else {
            this.group = Group.SERVICES;
        }
    }


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

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public String toString() {
        return "Record{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", shortName='" + shortName + '\'' +
                ", nickname='" + nickname + '\'' +
                ", group=" + group +
                ", email='" + email + '\'' +
                '}';
    }

    /**
     * Methods required for testing of main Record business methods
     * @return group of recorded user and its shortname
     */
    public Group getGroup() {
        return group;
    }

    public String getShortName() {
        return shortName;
    }
}
