package com.project.cruiser.entity;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Set;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "users")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column
    @NotEmpty(message = "Please, input your email")
    @Email(message = "Email format not valid")
    private String email;

    @Column(name = "first_name")
    @NotEmpty(message = "Please, input your first name")
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty(message = "Please, input your last name")
    private String lastName;

    @Column(nullable = false, length = 64)
    @Size(min = 1, max = 64, message = "Input password, please")
    @ToString.Exclude
    private String password;

    @Column(name = "money_amount", nullable = false)
    @Min(0)
    private Integer moneyAmount;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private RoleType role;

    @Column(name = "file_name")
    private String fileName;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private Set<BookingList> bookingLists;



}
