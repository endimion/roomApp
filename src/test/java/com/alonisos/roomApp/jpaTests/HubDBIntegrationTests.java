package com.alonisos.roomApp.jpaTests;

import com.alonisos.roomApp.model.Reservation.ReservationRepository;
import com.alonisos.roomApp.model.Reservation.ReservationStatusRepository;
import com.alonisos.roomApp.model.assignment.AssignmentRepository;
import com.alonisos.roomApp.model.assignment.AssignmentStatusRepostitory;
import com.alonisos.roomApp.model.employee.EmployeeRepository;
import com.alonisos.roomApp.model.employee.EmployeeTypeRepository;
import com.alonisos.roomApp.model.host.HostDMO;
import com.alonisos.roomApp.model.host.HostRepository;
import com.alonisos.roomApp.model.hub.HubDMO;
import com.alonisos.roomApp.model.hub.HubRepository;
import com.alonisos.roomApp.model.offeredService.OfferedServiceRepository;
import com.alonisos.roomApp.model.offeredService.ServiceTypeRepository;
import com.alonisos.roomApp.model.room.RoomRepository;
import com.alonisos.roomApp.model.roomService.RoomServiceRepository;
import com.alonisos.roomApp.model.serviceRequest.ServiceRequestRepository;
import com.alonisos.roomApp.model.vehicle.VehicleTypeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class HubDBIntegrationTests extends DatabaseTest {
    @Autowired
    private HostRepository hostRepository;

    @Autowired
    private HubRepository hubRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReservationStatusRepository reservationStatusRepository;

    @Autowired
    private ServiceTypeRepository serviceTypeRepository;
    @Autowired
    private OfferedServiceRepository offeredServiceRepository;
    @Autowired
    private VehicleTypeRepository vehicleTypeRepository;


    @Autowired
    private RoomServiceRepository roomServiceRepository;

    @Autowired
    private ServiceRequestRepository serviceRequestRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeTypeRepository employeeTypeRepository;

    @Autowired
    private AssignmentStatusRepostitory assignmentStatusRepostitory;
    @Autowired
    private AssignmentRepository assignmentRepository;
    @Test
    public void whenFindByName_thenReturnHub() {
        HostDMO host = new HostDMO();
        host.setName("nikos");
        host.setEmail("nikos@nikos.gr");
        this.hostRepository.save(host);
        HostDMO foundHost = hostRepository.findByName(host.getName());

        HubDMO hubDMO = new HubDMO();
        hubDMO.setHost(foundHost);
        hubDMO.setName("hubName");
        hubDMO.setLocation("location of hub");
        this.hubRepository.save(hubDMO);
        // when
        HubDMO found = hubRepository.findByName(hubDMO.getName());
        // then
        assertThat(found.getName())
                .isEqualTo(hubDMO.getName());
    }

    @Test
    public void whenHubsByHostId_thenReturnOneHub() {
        HostDMO host = new HostDMO();
        host.setName("nikos");
        host.setEmail("nikos@nikos.gr");
        this.hostRepository.save(host);
        HostDMO foundHost = hostRepository.findByName(host.getName());

        HubDMO hubDMO = new HubDMO();
        hubDMO.setHost(foundHost);
        hubDMO.setName("hubName");
        hubDMO.setLocation("location of hub");
        this.hubRepository.save(hubDMO);
        // when
        List<HubDMO> found = hubRepository.findByHostId(foundHost.getId());
        // then
        assertThat(found.size())
                .isEqualTo(1);
    }

}
