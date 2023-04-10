package com.alonisos.roomApp.model.Reservation;

import javax.persistence.*;

@Entity
@Table(name = "ReservationStatus")
public class ReservationStatusDMO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Name")
    private String name;

    // getters and setters
}

