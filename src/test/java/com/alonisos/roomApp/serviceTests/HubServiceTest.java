package com.alonisos.roomApp.serviceTests;
import com.alonisos.roomApp.model.Reservation.ReservationDMO;
import com.alonisos.roomApp.model.Reservation.ReservationStatusDMO;
import com.alonisos.roomApp.model.Reservation.ReservationRepository;
import com.alonisos.roomApp.model.host.HostDMO;
import com.alonisos.roomApp.model.host.HostRepository;
import com.alonisos.roomApp.model.hub.HubDMO;
import com.alonisos.roomApp.model.hub.HubRepository;
import com.alonisos.roomApp.model.room.RoomDMO;
import com.alonisos.roomApp.model.room.RoomRepository;
import com.alonisos.roomApp.model.serviceRequest.ServiceRequestRepository;
import com.alonisos.roomApp.service.impl.HubServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class HubServiceTest {

    @Mock
    private HubRepository hubRepository;

    @Mock
    private HostRepository hostRepository;

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private ServiceRequestRepository serviceRequestRepository;

    @InjectMocks
    private HubServiceImpl hubService;

    @Test
    public void testGetReservationsPerHubAndHostEmail() {
        Long hubId = 1L;
        String hostEmail = "host@example.com";
        HostDMO host = new HostDMO();
        host.setId(2L);
        host.setEmail(hostEmail);

        List<ReservationDMO> expectedReservations = new ArrayList<>();
        RoomDMO room1 = new RoomDMO();
        HubDMO hub1 = new HubDMO();
        hub1.setId(hubId);
        room1.setHub(hub1);
        ReservationDMO reservation1 = new ReservationDMO();
        reservation1.setRoom(room1);
        expectedReservations.add(reservation1);

        RoomDMO room2 = new RoomDMO();
        HubDMO hub2 = new HubDMO();
        hub2.setId(hubId);
        room2.setHub(hub2);
        ReservationDMO reservation2 = new ReservationDMO();
        reservation2.setRoom(room2);
        expectedReservations.add(reservation2);

        Mockito.when(hostRepository.findByEmail(hostEmail)).thenReturn(host);
        Mockito.when(reservationRepository.findAllByHostId(host.getId())).thenReturn(expectedReservations);

        List<ReservationDMO> actualReservations = hubService.getReservationsPerHubAndHostEmail(hubId, hostEmail);

        // This line of code is used in the unit test to verify that the getReservationsPerHubAndHostEmail method is
        // correctly calling the reservationRepository.findAllByHostId method with the appropriate argument.
        // By using Mockito's verify method, we can ensure that the method is called exactly once with the expected argument.
        // If it's not called, or if it's called with a different argument, the test will fail.
        Mockito.verify(reservationRepository).findAllByHostId(host.getId());
        //same
        Mockito.verify(hostRepository).findByEmail(hostEmail);

        //actual checking of results...
        assertEquals(expectedReservations, actualReservations);
    }
}