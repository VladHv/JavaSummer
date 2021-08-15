package com.project.cruiser.entity;

import javax.persistence.*;
import com.project.cruiser.entity.User;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "booking_list")
public class BookingList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "cruise_id")
    private Cruise cruise;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private BookingStatus status;

}
