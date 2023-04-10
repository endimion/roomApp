package com.alonisos.roomApp.model.Reservation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "reservation_status")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationStatusDMO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;


    @Column(name = "Name")
    private String name;

    // getters and setters
}

