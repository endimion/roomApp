package com.alonisos.roomApp.model.roomService;

import com.alonisos.roomApp.model.offeredService.OfferedServiceDMO;
import com.alonisos.roomApp.model.room.RoomDMO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "room_services")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomServiceDMO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "room_id", nullable = false)
    private RoomDMO room;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "service_id", nullable = false)
    private OfferedServiceDMO offeredService;
}

