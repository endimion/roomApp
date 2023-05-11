package com.alonisos.roomApp.model.room;

import com.alonisos.roomApp.model.hub.HubDMO;
import com.alonisos.roomApp.model.hub.HubTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
public class RoomTO {

    private Long id;
    private HubTO hub;
    private String name;
    private Integer numberOfBeds;

    // getters and setters
}