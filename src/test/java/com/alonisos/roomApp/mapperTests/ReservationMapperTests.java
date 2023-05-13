package com.alonisos.roomApp.mapperTests;

import com.alonisos.roomApp.model.Reservation.ReservationDMO;
import com.alonisos.roomApp.model.Reservation.ReservationTO;
import com.alonisos.roomApp.model.host.HostDMO;
import com.alonisos.roomApp.model.hub.HubDMO;
import com.alonisos.roomApp.model.room.RoomDMO;
import com.alonisos.roomApp.utils.wrappers.ReservationMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ReservationMapperTests {

    @Autowired
    ReservationMapper mapper;

    @Test
    public void testMappingReservationDMO2TO() {
        Long hubId = 1L;
        String hostEmail = "host@example.com";
        HostDMO host = new HostDMO();
        host.setId(2L);
        host.setEmail(hostEmail);

        RoomDMO room1 = new RoomDMO();
        HubDMO hub1 = new HubDMO();
        hub1.setId(hubId);
        room1.setHub(hub1);
        room1.setName("room1");
        room1.setNumberOfBeds(4);
        room1.setId(1L);
        ReservationDMO reservation1 = new ReservationDMO();
        reservation1.setRoom(room1);


        ReservationTO resrverationTO = mapper.toTO(reservation1);
        assertEquals(reservation1.getRoom().getHub().getId(),resrverationTO.getRoom().getHub().getId());

    }

}
