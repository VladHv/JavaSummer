package com.project.cruiserservlet.model.entity;

import java.util.Set;

public class User {

    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private Integer moneyAmount;
    private RoleType role;
    private String fileName;
    private Set<BookingList> bookingLists;
}
