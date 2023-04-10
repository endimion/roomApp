package com.alonisos.roomApp.model.Reservation;

import com.alonisos.roomApp.model.room.RoomDMO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Reservation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDMO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "room_id", nullable = false)
    private RoomDMO room;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "check_in", nullable = false)
    private Date checkIn;

    @Column(name = "check_out", nullable = false)
    private Date checkOut;

    @Column(name = "persons", nullable = false)
    private Integer persons;

    @Column(name = "children", nullable = false)
    private Integer children;

    @Column(name = "babies", nullable = false)
    private Integer babies;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "status", nullable = false)
    private ReservationStatusDMO status;

    @Column(name = "true_check_in")
    private Date trueCheckIn;

    @Column(name = "true_check_out")
    private Date trueCheckOut;

    // getters and setters
}