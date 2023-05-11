package com.alonisos.roomApp.model.Reservation;

import com.alonisos.roomApp.model.room.RoomDMO;
import com.alonisos.roomApp.model.room.RoomTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationTO {

    private Long id;
    private RoomTO room;
    private String email;
    private Date checkIn;
    private Date checkOut;
    private Integer persons;
    private Integer children;
    private Integer babies;
    private ReservationStatusTO status;
    private Date trueCheckIn;
    private Date trueCheckOut;

    // getters and setters
}