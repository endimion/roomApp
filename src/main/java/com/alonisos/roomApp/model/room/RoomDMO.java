package com.alonisos.roomApp.model.room;

import com.alonisos.roomApp.model.hub.HubDMO;

import javax.persistence.*;

@Entity
@Table(name = "Room")
public class RoomDMO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "HubId", nullable = false)
    private HubDMO hub;

    @Column(name = "Name", nullable = false, length = 50)
    private String name;

    @Column(name = "NumberOfBeds", nullable = false)
    private Integer numberOfBeds;

    // getters and setters
}