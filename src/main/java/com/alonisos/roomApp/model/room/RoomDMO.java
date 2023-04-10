package com.alonisos.roomApp.model.room;

import com.alonisos.roomApp.model.hub.HubDMO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Room")
@Getter
@Setter
public class RoomDMO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
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