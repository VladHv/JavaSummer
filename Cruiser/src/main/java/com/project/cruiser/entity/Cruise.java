package com.project.cruiser.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table (name = "cruises")
public class Cruise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long Id;

    @Column(name = "pass_capacity", nullable = false)
    @Size(min = 1)
    private Integer passCapacity;

    @Column(name = "route", nullable = false)
    @NotEmpty
    private String route;

    @Column(name = "price", nullable = false)
    @Size(min = 1)
    private Integer price;

    @Column(name = "start_date", nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @NotEmpty
    private LocalDateTime start;

    @Column(name = "end_date", nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @NotEmpty
    private LocalDateTime end;

    @OneToMany(mappedBy = "cruise")
    private Set<BookingList> bookingList;



}
