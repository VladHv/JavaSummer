package com.project.cruiserservlet.model.entity;

import java.time.LocalDateTime;
import java.util.Set;

public class Cruise {

    private Long Id;
    private Integer passCapacity;
    private String route;
    private Integer price;
    private LocalDateTime start;
    private LocalDateTime end;
    private Integer freePlaces;
    private Set<BookingList> bookingList;
}
