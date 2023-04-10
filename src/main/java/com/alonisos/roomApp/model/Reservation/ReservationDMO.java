package com.alonisos.roomApp.model.Reservation;

import com.alonisos.roomApp.model.room.RoomDMO;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Reservation")
public class ReservationDMO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "RoomId", nullable = false)
    private RoomDMO room;

    @Column(name = "Email", nullable = false, length = 50)
    private String email;

    @Column(name = "CheckIn", nullable = false)
    private Date checkIn;

    @Column(name = "CheckOut", nullable = false)
    private Date checkOut;

    @Column(name = "Persons", nullable = false)
    private Integer persons;

    @Column(name = "Children", nullable = false)
    private Integer children;

    @Column(name = "Babies", nullable = false)
    private Integer babies;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Status", nullable = false)
    private ReservationStatusDMO status;

    @Column(name = "TrueCheckIn")
    private Date trueCheckIn;

    @Column(name = "TrueCheckOut")
    private Date trueCheckOut;

    // getters and setters
}