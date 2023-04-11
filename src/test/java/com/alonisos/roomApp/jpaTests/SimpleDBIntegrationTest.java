package com.alonisos.roomApp.jpaTests;

import com.alonisos.roomApp.model.Reservation.ReservationDMO;
import com.alonisos.roomApp.model.Reservation.ReservationRepository;
import com.alonisos.roomApp.model.Reservation.ReservationStatusDMO;
import com.alonisos.roomApp.model.Reservation.ReservationStatusRepository;
import com.alonisos.roomApp.model.assignment.AssignmentDMO;
import com.alonisos.roomApp.model.assignment.AssignmentRepository;
import com.alonisos.roomApp.model.assignment.AssignmentStatusDMO;
import com.alonisos.roomApp.model.assignment.AssignmentStatusRepostitory;
import com.alonisos.roomApp.model.employee.EmployeeDMO;
import com.alonisos.roomApp.model.employee.EmployeeRepository;
import com.alonisos.roomApp.model.employee.EmployeeTypeDMO;
import com.alonisos.roomApp.model.employee.EmployeeTypeRepository;
import com.alonisos.roomApp.model.host.HostDMO;
import com.alonisos.roomApp.model.host.HostRepository;
import com.alonisos.roomApp.model.hub.HubDMO;
import com.alonisos.roomApp.model.hub.HubRepository;
import com.alonisos.roomApp.model.offeredService.OfferedServiceDMO;
import com.alonisos.roomApp.model.offeredService.OfferedServiceRepository;
import com.alonisos.roomApp.model.offeredService.ServiceTypeDMO;
import com.alonisos.roomApp.model.offeredService.ServiceTypeRepository;
import com.alonisos.roomApp.model.room.RoomDMO;
import com.alonisos.roomApp.model.room.RoomRepository;
import com.alonisos.roomApp.model.roomService.RoomServiceDMO;
import com.alonisos.roomApp.model.roomService.RoomServiceRepository;
import com.alonisos.roomApp.model.serviceRequest.ServiceRequestDMO;
import com.alonisos.roomApp.model.serviceRequest.ServiceRequestRepository;
import com.alonisos.roomApp.model.vehicle.VehicleTypeDMO;
import com.alonisos.roomApp.model.vehicle.VehicleTypeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SimpleDBIntegrationTest extends DatabaseTest {


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
    public void whenFindByName_thenReturnHost() {
        // given
        HostDMO host = new HostDMO();
        host.setName("nikos");
        host.setEmail("nikos@nikos.gr");
        this.hostRepository.save(host);

        // when
        HostDMO found = hostRepository.findByName(host.getName());

        // then
        assertThat(found.getName())
                .isEqualTo(host.getName());
    }

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


    @Test
    public void whenFindByName_thenRoom() {
        HostDMO host = new HostDMO();
        host.setName("nikos");
        host.setEmail("nikos@nikos.gr");
        this.hostRepository.save(host);
        HostDMO foundHost = hostRepository.findByName(host.getName());

        HubDMO hubDMO = new HubDMO();
        hubDMO.setHost(foundHost);
        hubDMO.setName("HUB 1");
        hubDMO.setLocation("location of hub");
        this.hubRepository.save(hubDMO);

        RoomDMO room = new RoomDMO();
        room.setName("Room 1");
        room.setNumberOfBeds(2);
        HubDMO foundHub = hubRepository.findByName(hubDMO.getName());
        room.setHub(foundHub);
        this.roomRepository.save(room);
        // when
        RoomDMO found = this.roomRepository.findByName(room.getName());
        // then
        assertThat(found.getName())
                .isEqualTo(room.getName());

        assertThat(found.getHub().getName()).isEqualTo(foundHub.getName());
        assertThat(found.getHub().getHost().getName()).isEqualTo(host.getName());

    }


    @Test
    public void whenHubFindRoomsByHostId_thenReturnOne() {
        HostDMO host = new HostDMO();
        host.setName("nikos");
        host.setEmail("nikos@nikos.gr");
        this.hostRepository.save(host);
        HostDMO foundHost = hostRepository.findByName(host.getName());

        HubDMO hubDMO = new HubDMO();
        hubDMO.setHost(foundHost);
        hubDMO.setName("HUB 1");
        hubDMO.setLocation("location of hub");
        this.hubRepository.save(hubDMO);

        RoomDMO room = new RoomDMO();
        room.setName("Room 1");
        room.setNumberOfBeds(2);
        HubDMO foundHub = hubRepository.findByName(hubDMO.getName());
        room.setHub(foundHub);
        this.roomRepository.save(room);
        // when
        List<RoomDMO> found = this.hubRepository.findRoomsByHostId(foundHost.getId());
        // then
        assertThat(found.size()).isEqualTo(1);

    }


    @Test
    public void whenFindByEmail_thenReservation() {
        HostDMO host = new HostDMO();
        host.setName("nikos");
        host.setEmail("nikos@nikos.gr");
        this.hostRepository.save(host);
        HostDMO foundHost = hostRepository.findByName(host.getName());

        HubDMO hubDMO = new HubDMO();
        hubDMO.setHost(foundHost);
        hubDMO.setName("HUB 1");
        hubDMO.setLocation("location of hub");
        this.hubRepository.save(hubDMO);

        RoomDMO room = new RoomDMO();
        room.setName("Room 1");
        room.setNumberOfBeds(2);
        HubDMO foundHub = hubRepository.findByName(hubDMO.getName());
        room.setHub(foundHub);
        this.roomRepository.save(room);
        RoomDMO foundRoom = this.roomRepository.findByName(room.getName());

        ReservationStatusDMO reservationStatus = new ReservationStatusDMO();
        reservationStatus.setName("FREE");
        this.reservationStatusRepository.save(reservationStatus);
        ReservationStatusDMO foundStatus = this.reservationStatusRepository.findByName("FREE");

        ReservationDMO reservation = new ReservationDMO();
        reservation.setEmail("client@test.gr");
        reservation.setBabies(0);
        reservation.setRoom(foundRoom);
        reservation.setChildren(0);
        reservation.setPersons(2);
        reservation.setStatus(foundStatus);


        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        try {
            date = new Date(formatter.parse("20-10-2023").getTime());
            reservation.setTrueCheckOut(date);
            reservation.setCheckIn(date);
            reservation.setCheckOut(date);

        } catch (Exception e) {
            e.printStackTrace();
        }
        this.reservationRepository.save(reservation);

        ReservationDMO foundReservation = this.reservationRepository.findByEmail(reservation.getEmail());

        assertThat(foundReservation.getEmail()).isEqualTo(reservation.getEmail());

    }


    @Test
    public void whenFindByRoom_thenReservation() {
        HostDMO host = new HostDMO();
        host.setName("nikos");
        host.setEmail("nikos@nikos.gr");
        this.hostRepository.save(host);
        HostDMO foundHost = hostRepository.findByName(host.getName());

        HubDMO hubDMO = new HubDMO();
        hubDMO.setHost(foundHost);
        hubDMO.setName("HUB 1");
        hubDMO.setLocation("location of hub");
        this.hubRepository.save(hubDMO);

        RoomDMO room = new RoomDMO();
        room.setName("Room 1");
        room.setNumberOfBeds(2);
        HubDMO foundHub = hubRepository.findByName(hubDMO.getName());
        room.setHub(foundHub);
        this.roomRepository.save(room);
        RoomDMO foundRoom = this.roomRepository.findByName(room.getName());

        ReservationStatusDMO reservationStatus = new ReservationStatusDMO();
        reservationStatus.setName("FREE");
        this.reservationStatusRepository.save(reservationStatus);
        ReservationStatusDMO foundStatus = this.reservationStatusRepository.findByName("FREE");

        ReservationDMO reservation = new ReservationDMO();
        reservation.setEmail("client@test.gr");
        reservation.setBabies(0);
        reservation.setRoom(foundRoom);
        reservation.setChildren(0);
        reservation.setPersons(2);
        reservation.setStatus(foundStatus);


        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        try {
            date = new Date(formatter.parse("20-10-2023").getTime());
            reservation.setTrueCheckOut(date);
            reservation.setCheckIn(date);
            reservation.setCheckOut(date);

        } catch (Exception e) {
            e.printStackTrace();
        }
        this.reservationRepository.save(reservation);

        ReservationDMO foundReservation = this.reservationRepository.findByRoom(foundRoom);

        assertThat(foundReservation.getEmail()).isEqualTo(reservation.getEmail());

    }

    @Test
    public void whenReservationFindByHostId_thenReturnOne() {
        HostDMO host = new HostDMO();
        host.setName("nikos");
        host.setEmail("nikos@nikos.gr");
        this.hostRepository.save(host);
        HostDMO foundHost = hostRepository.findByName(host.getName());

        HubDMO hubDMO = new HubDMO();
        hubDMO.setHost(foundHost);
        hubDMO.setName("HUB 1");
        hubDMO.setLocation("location of hub");
        this.hubRepository.save(hubDMO);

        RoomDMO room = new RoomDMO();
        room.setName("Room 1");
        room.setNumberOfBeds(2);
        HubDMO foundHub = hubRepository.findByName(hubDMO.getName());
        room.setHub(foundHub);
        this.roomRepository.save(room);

        RoomDMO foundRoom = this.roomRepository.findByName(room.getName());

        ReservationStatusDMO reservationStatus = new ReservationStatusDMO();
        reservationStatus.setName("FREE");
        this.reservationStatusRepository.save(reservationStatus);
        ReservationStatusDMO foundStatus = this.reservationStatusRepository.findByName("FREE");

        ReservationDMO reservation = new ReservationDMO();
        reservation.setEmail("client@test.gr");
        reservation.setBabies(0);
        reservation.setRoom(foundRoom);
        reservation.setChildren(0);
        reservation.setPersons(2);
        reservation.setStatus(foundStatus);


        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        try {
            date = new Date(formatter.parse("20-10-2023").getTime());
            reservation.setTrueCheckOut(date);
            reservation.setCheckIn(date);
            reservation.setCheckOut(date);

        } catch (Exception e) {
            e.printStackTrace();
        }
        this.reservationRepository.save(reservation);

        // when
        List<ReservationDMO> found = this.reservationRepository.findAllByHostId(foundHost.getId());
        // then
        assertThat(found.size()).isEqualTo(1);
        assertThat(found.get(0).getEmail()).isEqualTo(reservation.getEmail());

    }

    @Test
    public void whenReservationFindByHostIdAndRoomId_thenReturnOne() {
        HostDMO host = new HostDMO();
        host.setName("nikos");
        host.setEmail("nikos@nikos.gr");
        this.hostRepository.save(host);
        HostDMO foundHost = hostRepository.findByName(host.getName());

        HubDMO hubDMO = new HubDMO();
        hubDMO.setHost(foundHost);
        hubDMO.setName("HUB 1");
        hubDMO.setLocation("location of hub");
        this.hubRepository.save(hubDMO);

        RoomDMO room = new RoomDMO();
        room.setName("Room 1");
        room.setNumberOfBeds(2);
        HubDMO foundHub = hubRepository.findByName(hubDMO.getName());
        room.setHub(foundHub);
        this.roomRepository.save(room);

        RoomDMO foundRoom = this.roomRepository.findByName(room.getName());

        ReservationStatusDMO reservationStatus = new ReservationStatusDMO();
        reservationStatus.setName("FREE");
        this.reservationStatusRepository.save(reservationStatus);
        ReservationStatusDMO foundStatus = this.reservationStatusRepository.findByName("FREE");

        ReservationDMO reservation = new ReservationDMO();
        reservation.setEmail("client@test.gr");
        reservation.setBabies(0);
        reservation.setRoom(foundRoom);
        reservation.setChildren(0);
        reservation.setPersons(2);
        reservation.setStatus(foundStatus);


        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        try {
            date = new Date(formatter.parse("20-10-2023").getTime());
            reservation.setTrueCheckOut(date);
            reservation.setCheckIn(date);
            reservation.setCheckOut(date);

        } catch (Exception e) {
            e.printStackTrace();
        }
        this.reservationRepository.save(reservation);

        // when
        List<ReservationDMO> found = this.reservationRepository.findAllByHostIdAndRoomId(foundHost.getId(), foundRoom.getId());
        // then
        assertThat(found.size()).isEqualTo(1);
        assertThat(found.get(0).getEmail()).isEqualTo(reservation.getEmail());

    }

    @Test
    public void whenReservationFindByHubId_thenReturnOne() {
        HostDMO host = new HostDMO();
        host.setName("nikos");
        host.setEmail("nikos@nikos.gr");
        this.hostRepository.save(host);
        HostDMO foundHost = hostRepository.findByName(host.getName());

        HubDMO hubDMO = new HubDMO();
        hubDMO.setHost(foundHost);
        hubDMO.setName("HUB 1");
        hubDMO.setLocation("location of hub");
        this.hubRepository.save(hubDMO);

        RoomDMO room = new RoomDMO();
        room.setName("Room 1");
        room.setNumberOfBeds(2);
        HubDMO foundHub = hubRepository.findByName(hubDMO.getName());
        room.setHub(foundHub);
        this.roomRepository.save(room);

        RoomDMO foundRoom = this.roomRepository.findByName(room.getName());

        ReservationStatusDMO reservationStatus = new ReservationStatusDMO();
        reservationStatus.setName("FREE");
        this.reservationStatusRepository.save(reservationStatus);
        ReservationStatusDMO foundStatus = this.reservationStatusRepository.findByName("FREE");

        ReservationDMO reservation = new ReservationDMO();
        reservation.setEmail("client@test.gr");
        reservation.setBabies(0);
        reservation.setRoom(foundRoom);
        reservation.setChildren(0);
        reservation.setPersons(2);
        reservation.setStatus(foundStatus);


        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        try {
            date = new Date(formatter.parse("20-10-2023").getTime());
            reservation.setTrueCheckOut(date);
            reservation.setCheckIn(date);
            reservation.setCheckOut(date);

        } catch (Exception e) {
            e.printStackTrace();
        }
        this.reservationRepository.save(reservation);

        // when
        List<ReservationDMO> found = this.reservationRepository.findAllByHubId(foundHub.getId());
        // then
        assertThat(found.size()).isEqualTo(1);
        assertThat(found.get(0).getEmail()).isEqualTo(reservation.getEmail());

    }

    @Test
    public void whenOfferedServiceFindName_thenReturn() {
        ServiceTypeDMO serviceType = new ServiceTypeDMO();
        serviceType.setName("Service 1");
        this.serviceTypeRepository.save(serviceType);
        ServiceTypeDMO foundService = this.serviceTypeRepository.findByName(serviceType.getName());

        VehicleTypeDMO vehicleType = new VehicleTypeDMO();
        vehicleType.setName("Car");
        this.vehicleTypeRepository.save(vehicleType);
        VehicleTypeDMO foundVehicle = this.vehicleTypeRepository.findByName("Car");

        OfferedServiceDMO offeredService = new OfferedServiceDMO();
        offeredService.setName("Service Name");
        offeredService.setServiceType(foundService);
        offeredService.setVehicleType(foundVehicle);
        this.offeredServiceRepository.save(offeredService);

        // when
        OfferedServiceDMO found = this.offeredServiceRepository.findByName(offeredService.getName());
        // then
        assertThat(found.getServiceType().getName()).isEqualTo(foundService.getName());
        assertThat(found.getVehicleType().getName()).isEqualTo(vehicleType.getName());

    }

    @Test
    public void whenRoomServiceFindByRoomId_thenReturnOne() {
        ServiceTypeDMO serviceType = new ServiceTypeDMO();
        serviceType.setName("Service 1");
        this.serviceTypeRepository.save(serviceType);
        ServiceTypeDMO foundService = this.serviceTypeRepository.findByName(serviceType.getName());

        VehicleTypeDMO vehicleType = new VehicleTypeDMO();
        vehicleType.setName("Car");
        this.vehicleTypeRepository.save(vehicleType);
        VehicleTypeDMO foundVehicle = this.vehicleTypeRepository.findByName("Car");

        OfferedServiceDMO offeredService = new OfferedServiceDMO();
        offeredService.setName("Service Name");
        offeredService.setServiceType(foundService);
        offeredService.setVehicleType(foundVehicle);
        this.offeredServiceRepository.save(offeredService);
        OfferedServiceDMO foundOfferedService = this.offeredServiceRepository.findByName(offeredService.getName());


        HostDMO host = new HostDMO();
        host.setName("nikos");
        host.setEmail("nikos@nikos.gr");
        this.hostRepository.save(host);
        HostDMO foundHost = hostRepository.findByName(host.getName());

        HubDMO hubDMO = new HubDMO();
        hubDMO.setHost(foundHost);
        hubDMO.setName("HUB 1");
        hubDMO.setLocation("location of hub");
        this.hubRepository.save(hubDMO);

        RoomDMO room = new RoomDMO();
        room.setName("Room 1");
        room.setNumberOfBeds(2);
        HubDMO foundHub = hubRepository.findByName(hubDMO.getName());
        room.setHub(foundHub);
        this.roomRepository.save(room);

        RoomDMO foundRoom = this.roomRepository.findByName(room.getName());


        RoomServiceDMO roomServiceDMO = new RoomServiceDMO();
        roomServiceDMO.setOfferedService(foundOfferedService);
        roomServiceDMO.setRoom(foundRoom);
        this.roomServiceRepository.save(roomServiceDMO);

        // when
        List<RoomServiceDMO> found = this.roomServiceRepository.findByRoomId(foundRoom.getId());
        // then
        assertThat(found.size()).isEqualTo(1);

    }


    @Test
    public void whenServiceRequestFindByRoomId_thenReturnOne() {
        ServiceTypeDMO serviceType = new ServiceTypeDMO();
        serviceType.setName("Service 1");
        this.serviceTypeRepository.save(serviceType);
        ServiceTypeDMO foundService = this.serviceTypeRepository.findByName(serviceType.getName());

        VehicleTypeDMO vehicleType = new VehicleTypeDMO();
        vehicleType.setName("Car");
        this.vehicleTypeRepository.save(vehicleType);
        VehicleTypeDMO foundVehicle = this.vehicleTypeRepository.findByName("Car");

        OfferedServiceDMO offeredService = new OfferedServiceDMO();
        offeredService.setName("Service Name");
        offeredService.setServiceType(foundService);
        offeredService.setVehicleType(foundVehicle);
        this.offeredServiceRepository.save(offeredService);
        OfferedServiceDMO foundOfferedService = this.offeredServiceRepository.findByName(offeredService.getName());


        HostDMO host = new HostDMO();
        host.setName("nikos");
        host.setEmail("nikos@nikos.gr");
        this.hostRepository.save(host);
        HostDMO foundHost = hostRepository.findByName(host.getName());

        HubDMO hubDMO = new HubDMO();
        hubDMO.setHost(foundHost);
        hubDMO.setName("HUB 1");
        hubDMO.setLocation("location of hub");
        this.hubRepository.save(hubDMO);

        RoomDMO room = new RoomDMO();
        room.setName("Room 1");
        room.setNumberOfBeds(2);
        HubDMO foundHub = hubRepository.findByName(hubDMO.getName());
        room.setHub(foundHub);
        this.roomRepository.save(room);

        RoomDMO foundRoom = this.roomRepository.findByName(room.getName());

        ReservationStatusDMO reservationStatus = new ReservationStatusDMO();
        reservationStatus.setName("FREE");
        this.reservationStatusRepository.save(reservationStatus);
        ReservationStatusDMO foundStatus = this.reservationStatusRepository.findByName("FREE");

        ReservationDMO reservation = new ReservationDMO();
        reservation.setEmail("client@test.gr");
        reservation.setBabies(0);
        reservation.setRoom(foundRoom);
        reservation.setChildren(0);
        reservation.setPersons(2);
        reservation.setStatus(foundStatus);


        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        try {
            date = new Date(formatter.parse("20-10-2023").getTime());
            reservation.setTrueCheckOut(date);
            reservation.setCheckIn(date);
            reservation.setCheckOut(date);

        } catch (Exception e) {
            e.printStackTrace();
        }
        this.reservationRepository.save(reservation);

        ReservationDMO foundReservation = this.reservationRepository.findByEmail(reservation.getEmail());

        ServiceRequestDMO serviceRequest = new ServiceRequestDMO();
        serviceRequest.setOfferedService(foundOfferedService);
        serviceRequest.setReservation(foundReservation);
        serviceRequest.setEmail("client@test.gr");
        this.serviceRequestRepository.save(serviceRequest);


        // when
        List<ServiceRequestDMO> found = this.serviceRequestRepository.findAllByRoomId(foundRoom.getId());
        // then
        assertThat(found.size()).isEqualTo(1);

    }


    @Test
    public void whenEmployeeFindByPhoneNumber_thenReturn() {

        EmployeeTypeDMO employeeTypeDMO = new EmployeeTypeDMO();
        employeeTypeDMO.setName("slave");
        this.employeeTypeRepository.save(employeeTypeDMO);
        EmployeeTypeDMO foundType = this.employeeTypeRepository.findByName(employeeTypeDMO.getName());

        HostDMO host = new HostDMO();
        host.setEmail("host@test.gr");
        host.setName("Host Name");
        this.hostRepository.save(host);
        HostDMO foundHost = this.hostRepository.findByEmail(host.getEmail());

        VehicleTypeDMO vehicleType = new VehicleTypeDMO();
        vehicleType.setName("bike");
        this.vehicleTypeRepository.save(vehicleType);
        VehicleTypeDMO foundVehicle = this.vehicleTypeRepository.findByName(vehicleType.getName());

        EmployeeDMO employee = new EmployeeDMO();
        employee.setPhoneNumber("6943800000");
        employee.setEmail("this@that.gr");
        employee.setType(foundType);
        employee.setName("John Doe");
        employee.setHost(foundHost);
        employee.setVehicleType(foundVehicle);
        employee.setHasVehicle(true);
        this.employeeRepository.save(employee);


        // when
        EmployeeDMO found = this.employeeRepository.findByPhoneNumber(employee.getPhoneNumber());
        // then
        assertThat(found.getName()).isEqualTo(employee.getName());

    }

    @Test
    public void whenEmployeeFindAllHostId_thenReturnOne() {

        EmployeeTypeDMO employeeTypeDMO = new EmployeeTypeDMO();
        employeeTypeDMO.setName("slave");
        this.employeeTypeRepository.save(employeeTypeDMO);
        EmployeeTypeDMO foundType = this.employeeTypeRepository.findByName(employeeTypeDMO.getName());

        HostDMO host = new HostDMO();
        host.setEmail("host@test.gr");
        host.setName("Host Name");
        this.hostRepository.save(host);
        HostDMO foundHost = this.hostRepository.findByEmail(host.getEmail());

        VehicleTypeDMO vehicleType = new VehicleTypeDMO();
        vehicleType.setName("bike");
        this.vehicleTypeRepository.save(vehicleType);
        VehicleTypeDMO foundVehicle = this.vehicleTypeRepository.findByName(vehicleType.getName());

        EmployeeDMO employee = new EmployeeDMO();
        employee.setPhoneNumber("6943800000");
        employee.setEmail("this@that.gr");
        employee.setType(foundType);
        employee.setName("John Doe");
        employee.setHost(foundHost);
        employee.setVehicleType(foundVehicle);
        employee.setHasVehicle(true);
        this.employeeRepository.save(employee);


        // when
        List<EmployeeDMO> found = this.employeeRepository.findAllEmployeesByHostId(foundHost.getId());
        // then
        assertThat(found.size()).isEqualTo(1);

    }


    @Test
    public void whenAssignmentFindByRoomId_thenReturnOne() {
        HostDMO host = new HostDMO();
        host.setName("nikos");
        host.setEmail("nikos@nikos.gr");
        this.hostRepository.save(host);
        HostDMO foundHost = hostRepository.findByName(host.getName());

        HubDMO hubDMO = new HubDMO();
        hubDMO.setHost(foundHost);
        hubDMO.setName("HUB 1");
        hubDMO.setLocation("location of hub");
        this.hubRepository.save(hubDMO);

        RoomDMO room = new RoomDMO();
        room.setName("Room 1");
        room.setNumberOfBeds(2);
        HubDMO foundHub = hubRepository.findByName(hubDMO.getName());
        room.setHub(foundHub);
        this.roomRepository.save(room);
        RoomDMO foundRoom = this.roomRepository.findByName(room.getName());

        ReservationStatusDMO reservationStatus = new ReservationStatusDMO();
        reservationStatus.setName("FREE");
        this.reservationStatusRepository.save(reservationStatus);
        ReservationStatusDMO foundStatus = this.reservationStatusRepository.findByName("FREE");

        ReservationDMO reservation = new ReservationDMO();
        reservation.setEmail("client@test.gr");
        reservation.setBabies(0);
        reservation.setRoom(foundRoom);
        reservation.setChildren(0);
        reservation.setPersons(2);
        reservation.setStatus(foundStatus);


        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        try {
            date = new Date(formatter.parse("20-10-2023").getTime());
            reservation.setTrueCheckOut(date);
            reservation.setCheckIn(date);
            reservation.setCheckOut(date);

        } catch (Exception e) {
            e.printStackTrace();
        }
        this.reservationRepository.save(reservation);
        ReservationDMO foundReservation = this.reservationRepository.findByEmail(reservation.getEmail());

        ServiceTypeDMO serviceTypeDMO = new ServiceTypeDMO();
        serviceTypeDMO.setName("Room Service");
        this.serviceTypeRepository.save(serviceTypeDMO);
        ServiceTypeDMO foundServiceType = this.serviceTypeRepository.findByName(serviceTypeDMO.getName());

        VehicleTypeDMO vehicleType = new VehicleTypeDMO();
        vehicleType.setName("bike");
        this.vehicleTypeRepository.save(vehicleType);
        VehicleTypeDMO foundVehicleType = this.vehicleTypeRepository.findByName(vehicleType.getName());

        OfferedServiceDMO offeredService = new OfferedServiceDMO();
        offeredService.setName("Service 1");
        offeredService.setServiceType(foundServiceType);
        offeredService.setVehicleType(foundVehicleType);
        this.offeredServiceRepository.save(offeredService);
        OfferedServiceDMO foundService = this.offeredServiceRepository.findByName(offeredService.getName());

        ServiceRequestDMO requestDMO = new ServiceRequestDMO();
        requestDMO.setEmail("test@test.gr");
        requestDMO.setReservation(foundReservation);
        requestDMO.setOfferedService(foundService);
        this.serviceRequestRepository.save(requestDMO);
        ServiceRequestDMO foundServiceRequest = this.serviceRequestRepository.findAllByRoomId(foundRoom.getId()).get(0);
//
//        assertThat(foundReservation.getEmail()).isEqualTo(reservation.getEmail());
        AssignmentStatusDMO assignmentStatusDMO = new AssignmentStatusDMO();
        assignmentStatusDMO.setStatus("OK");
        this.assignmentStatusRepostitory.save(assignmentStatusDMO);
        AssignmentStatusDMO foundAssignmentStatus = this.assignmentStatusRepostitory.findByStatus("OK");

        EmployeeTypeDMO empType = new EmployeeTypeDMO();
        empType.setName("slave");
        this.employeeTypeRepository.save(empType);
        EmployeeTypeDMO foundEmpType = this.employeeTypeRepository.findByName(empType.getName());

        EmployeeDMO employee = new EmployeeDMO();
        employee.setHasVehicle(false);
        employee.setHost(foundHost);
        employee.setEmail("emp@test.gr");
        employee.setPhoneNumber("123");
        employee.setName("test name");
        employee.setType(foundEmpType);
        employee.setVehicleType(foundVehicleType);
        this.employeeRepository.save(employee);
        EmployeeDMO foundEmployee = this.employeeRepository.findByPhoneNumber(employee.getPhoneNumber());

        AssignmentDMO assignment = new AssignmentDMO();
        assignment.setEmployee(employee);
        assignment.setRequest(foundServiceRequest);
        assignment.setStatus(foundAssignmentStatus);
        this.assignmentRepository.save(assignment);

        // when
        List<AssignmentDMO> found = this.assignmentRepository.findAllByRoomId(foundRoom.getId());
        // then
        assertThat(found.size()).isEqualTo(1);


    }


    @Test
    public void whenFindByEmployee_thenReturnOne() {
        HostDMO host = new HostDMO();
        host.setName("nikos");
        host.setEmail("nikos@nikos.gr");
        this.hostRepository.save(host);
        HostDMO foundHost = hostRepository.findByName(host.getName());

        HubDMO hubDMO = new HubDMO();
        hubDMO.setHost(foundHost);
        hubDMO.setName("HUB 1");
        hubDMO.setLocation("location of hub");
        this.hubRepository.save(hubDMO);

        RoomDMO room = new RoomDMO();
        room.setName("Room 1");
        room.setNumberOfBeds(2);
        HubDMO foundHub = hubRepository.findByName(hubDMO.getName());
        room.setHub(foundHub);
        this.roomRepository.save(room);
        RoomDMO foundRoom = this.roomRepository.findByName(room.getName());

        ReservationStatusDMO reservationStatus = new ReservationStatusDMO();
        reservationStatus.setName("FREE");
        this.reservationStatusRepository.save(reservationStatus);
        ReservationStatusDMO foundStatus = this.reservationStatusRepository.findByName("FREE");

        ReservationDMO reservation = new ReservationDMO();
        reservation.setEmail("client@test.gr");
        reservation.setBabies(0);
        reservation.setRoom(foundRoom);
        reservation.setChildren(0);
        reservation.setPersons(2);
        reservation.setStatus(foundStatus);


        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        try {
            date = new Date(formatter.parse("20-10-2023").getTime());
            reservation.setTrueCheckOut(date);
            reservation.setCheckIn(date);
            reservation.setCheckOut(date);

        } catch (Exception e) {
            e.printStackTrace();
        }
        this.reservationRepository.save(reservation);
        ReservationDMO foundReservation = this.reservationRepository.findByEmail(reservation.getEmail());

        ServiceTypeDMO serviceTypeDMO = new ServiceTypeDMO();
        serviceTypeDMO.setName("Room Service");
        this.serviceTypeRepository.save(serviceTypeDMO);
        ServiceTypeDMO foundServiceType = this.serviceTypeRepository.findByName(serviceTypeDMO.getName());

        VehicleTypeDMO vehicleType = new VehicleTypeDMO();
        vehicleType.setName("bike");
        this.vehicleTypeRepository.save(vehicleType);
        VehicleTypeDMO foundVehicleType = this.vehicleTypeRepository.findByName(vehicleType.getName());

        OfferedServiceDMO offeredService = new OfferedServiceDMO();
        offeredService.setName("Service 1");
        offeredService.setServiceType(foundServiceType);
        offeredService.setVehicleType(foundVehicleType);
        this.offeredServiceRepository.save(offeredService);
        OfferedServiceDMO foundService = this.offeredServiceRepository.findByName(offeredService.getName());

        ServiceRequestDMO requestDMO = new ServiceRequestDMO();
        requestDMO.setEmail("test@test.gr");
        requestDMO.setReservation(foundReservation);
        requestDMO.setOfferedService(foundService);
        this.serviceRequestRepository.save(requestDMO);
        ServiceRequestDMO foundServiceRequest = this.serviceRequestRepository.findAllByRoomId(foundRoom.getId()).get(0);
//
//        assertThat(foundReservation.getEmail()).isEqualTo(reservation.getEmail());
        AssignmentStatusDMO assignmentStatusDMO = new AssignmentStatusDMO();
        assignmentStatusDMO.setStatus("OK");
        this.assignmentStatusRepostitory.save(assignmentStatusDMO);
        AssignmentStatusDMO foundAssignmentStatus = this.assignmentStatusRepostitory.findByStatus("OK");

        EmployeeTypeDMO empType = new EmployeeTypeDMO();
        empType.setName("slave");
        this.employeeTypeRepository.save(empType);
        EmployeeTypeDMO foundEmpType = this.employeeTypeRepository.findByName(empType.getName());

        EmployeeDMO employee = new EmployeeDMO();
        employee.setHasVehicle(false);
        employee.setHost(foundHost);
        employee.setEmail("emp@test.gr");
        employee.setPhoneNumber("123");
        employee.setName("test name");
        employee.setType(foundEmpType);
        employee.setVehicleType(foundVehicleType);
        this.employeeRepository.save(employee);
        EmployeeDMO foundEmployee = this.employeeRepository.findByPhoneNumber(employee.getPhoneNumber());

        AssignmentDMO assignment = new AssignmentDMO();
        assignment.setEmployee(employee);
        assignment.setRequest(foundServiceRequest);
        assignment.setStatus(foundAssignmentStatus);
        this.assignmentRepository.save(assignment);

        // when
        List<AssignmentDMO> found = this.assignmentRepository.findAllByEmployee(foundEmployee);
        // then
        assertThat(found.size()).isEqualTo(1);


    }


    @Test
    public void whenFindByHost_thenReturnOne() {
        HostDMO host = new HostDMO();
        host.setName("nikos");
        host.setEmail("nikos@nikos.gr");
        this.hostRepository.save(host);
        HostDMO foundHost = hostRepository.findByName(host.getName());

        HubDMO hubDMO = new HubDMO();
        hubDMO.setHost(foundHost);
        hubDMO.setName("HUB 1");
        hubDMO.setLocation("location of hub");
        this.hubRepository.save(hubDMO);

        RoomDMO room = new RoomDMO();
        room.setName("Room 1");
        room.setNumberOfBeds(2);
        HubDMO foundHub = hubRepository.findByName(hubDMO.getName());
        room.setHub(foundHub);
        this.roomRepository.save(room);
        RoomDMO foundRoom = this.roomRepository.findByName(room.getName());

        ReservationStatusDMO reservationStatus = new ReservationStatusDMO();
        reservationStatus.setName("FREE");
        this.reservationStatusRepository.save(reservationStatus);
        ReservationStatusDMO foundStatus = this.reservationStatusRepository.findByName("FREE");

        ReservationDMO reservation = new ReservationDMO();
        reservation.setEmail("client@test.gr");
        reservation.setBabies(0);
        reservation.setRoom(foundRoom);
        reservation.setChildren(0);
        reservation.setPersons(2);
        reservation.setStatus(foundStatus);


        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        try {
            date = new Date(formatter.parse("20-10-2023").getTime());
            reservation.setTrueCheckOut(date);
            reservation.setCheckIn(date);
            reservation.setCheckOut(date);

        } catch (Exception e) {
            e.printStackTrace();
        }
        this.reservationRepository.save(reservation);
        ReservationDMO foundReservation = this.reservationRepository.findByEmail(reservation.getEmail());

        ServiceTypeDMO serviceTypeDMO = new ServiceTypeDMO();
        serviceTypeDMO.setName("Room Service");
        this.serviceTypeRepository.save(serviceTypeDMO);
        ServiceTypeDMO foundServiceType = this.serviceTypeRepository.findByName(serviceTypeDMO.getName());

        VehicleTypeDMO vehicleType = new VehicleTypeDMO();
        vehicleType.setName("bike");
        this.vehicleTypeRepository.save(vehicleType);
        VehicleTypeDMO foundVehicleType = this.vehicleTypeRepository.findByName(vehicleType.getName());

        OfferedServiceDMO offeredService = new OfferedServiceDMO();
        offeredService.setName("Service 1");
        offeredService.setServiceType(foundServiceType);
        offeredService.setVehicleType(foundVehicleType);
        this.offeredServiceRepository.save(offeredService);
        OfferedServiceDMO foundService = this.offeredServiceRepository.findByName(offeredService.getName());

        ServiceRequestDMO requestDMO = new ServiceRequestDMO();
        requestDMO.setEmail("test@test.gr");
        requestDMO.setReservation(foundReservation);
        requestDMO.setOfferedService(foundService);
        this.serviceRequestRepository.save(requestDMO);
        ServiceRequestDMO foundServiceRequest = this.serviceRequestRepository.findAllByRoomId(foundRoom.getId()).get(0);
//
//        assertThat(foundReservation.getEmail()).isEqualTo(reservation.getEmail());
        AssignmentStatusDMO assignmentStatusDMO = new AssignmentStatusDMO();
        assignmentStatusDMO.setStatus("OK");
        this.assignmentStatusRepostitory.save(assignmentStatusDMO);
        AssignmentStatusDMO foundAssignmentStatus = this.assignmentStatusRepostitory.findByStatus("OK");

        EmployeeTypeDMO empType = new EmployeeTypeDMO();
        empType.setName("slave");
        this.employeeTypeRepository.save(empType);
        EmployeeTypeDMO foundEmpType = this.employeeTypeRepository.findByName(empType.getName());

        EmployeeDMO employee = new EmployeeDMO();
        employee.setHasVehicle(false);
        employee.setHost(foundHost);
        employee.setEmail("emp@test.gr");
        employee.setPhoneNumber("123");
        employee.setName("test name");
        employee.setType(foundEmpType);
        employee.setVehicleType(foundVehicleType);
        this.employeeRepository.save(employee);
        EmployeeDMO foundEmployee = this.employeeRepository.findByPhoneNumber(employee.getPhoneNumber());

        AssignmentDMO assignment = new AssignmentDMO();
        assignment.setEmployee(employee);
        assignment.setRequest(foundServiceRequest);
        assignment.setStatus(foundAssignmentStatus);
        this.assignmentRepository.save(assignment);

        // when
        List<AssignmentDMO> found = this.assignmentRepository.findAllByHost(foundHost.getId());
        // then
        assertThat(found.size()).isEqualTo(1);


    }


    @Test
    public void whenFindByHub_thenReturnOne() {
        HostDMO host = new HostDMO();
        host.setName("nikos");
        host.setEmail("nikos@nikos.gr");
        this.hostRepository.save(host);
        HostDMO foundHost = hostRepository.findByName(host.getName());

        HubDMO hubDMO = new HubDMO();
        hubDMO.setHost(foundHost);
        hubDMO.setName("HUB 1");
        hubDMO.setLocation("location of hub");
        this.hubRepository.save(hubDMO);

        RoomDMO room = new RoomDMO();
        room.setName("Room 1");
        room.setNumberOfBeds(2);
        HubDMO foundHub = hubRepository.findByName(hubDMO.getName());
        room.setHub(foundHub);
        this.roomRepository.save(room);
        RoomDMO foundRoom = this.roomRepository.findByName(room.getName());

        ReservationStatusDMO reservationStatus = new ReservationStatusDMO();
        reservationStatus.setName("FREE");
        this.reservationStatusRepository.save(reservationStatus);
        ReservationStatusDMO foundStatus = this.reservationStatusRepository.findByName("FREE");

        ReservationDMO reservation = new ReservationDMO();
        reservation.setEmail("client@test.gr");
        reservation.setBabies(0);
        reservation.setRoom(foundRoom);
        reservation.setChildren(0);
        reservation.setPersons(2);
        reservation.setStatus(foundStatus);


        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        try {
            date = new Date(formatter.parse("20-10-2023").getTime());
            reservation.setTrueCheckOut(date);
            reservation.setCheckIn(date);
            reservation.setCheckOut(date);

        } catch (Exception e) {
            e.printStackTrace();
        }
        this.reservationRepository.save(reservation);
        ReservationDMO foundReservation = this.reservationRepository.findByEmail(reservation.getEmail());

        ServiceTypeDMO serviceTypeDMO = new ServiceTypeDMO();
        serviceTypeDMO.setName("Room Service");
        this.serviceTypeRepository.save(serviceTypeDMO);
        ServiceTypeDMO foundServiceType = this.serviceTypeRepository.findByName(serviceTypeDMO.getName());

        VehicleTypeDMO vehicleType = new VehicleTypeDMO();
        vehicleType.setName("bike");
        this.vehicleTypeRepository.save(vehicleType);
        VehicleTypeDMO foundVehicleType = this.vehicleTypeRepository.findByName(vehicleType.getName());

        OfferedServiceDMO offeredService = new OfferedServiceDMO();
        offeredService.setName("Service 1");
        offeredService.setServiceType(foundServiceType);
        offeredService.setVehicleType(foundVehicleType);
        this.offeredServiceRepository.save(offeredService);
        OfferedServiceDMO foundService = this.offeredServiceRepository.findByName(offeredService.getName());

        ServiceRequestDMO requestDMO = new ServiceRequestDMO();
        requestDMO.setEmail("test@test.gr");
        requestDMO.setReservation(foundReservation);
        requestDMO.setOfferedService(foundService);
        this.serviceRequestRepository.save(requestDMO);
        ServiceRequestDMO foundServiceRequest = this.serviceRequestRepository.findAllByRoomId(foundRoom.getId()).get(0);
//
//        assertThat(foundReservation.getEmail()).isEqualTo(reservation.getEmail());
        AssignmentStatusDMO assignmentStatusDMO = new AssignmentStatusDMO();
        assignmentStatusDMO.setStatus("OK");
        this.assignmentStatusRepostitory.save(assignmentStatusDMO);
        AssignmentStatusDMO foundAssignmentStatus = this.assignmentStatusRepostitory.findByStatus("OK");

        EmployeeTypeDMO empType = new EmployeeTypeDMO();
        empType.setName("slave");
        this.employeeTypeRepository.save(empType);
        EmployeeTypeDMO foundEmpType = this.employeeTypeRepository.findByName(empType.getName());

        EmployeeDMO employee = new EmployeeDMO();
        employee.setHasVehicle(false);
        employee.setHost(foundHost);
        employee.setEmail("emp@test.gr");
        employee.setPhoneNumber("123");
        employee.setName("test name");
        employee.setType(foundEmpType);
        employee.setVehicleType(foundVehicleType);
        this.employeeRepository.save(employee);
        EmployeeDMO foundEmployee = this.employeeRepository.findByPhoneNumber(employee.getPhoneNumber());

        AssignmentDMO assignment = new AssignmentDMO();
        assignment.setEmployee(employee);
        assignment.setRequest(foundServiceRequest);
        assignment.setStatus(foundAssignmentStatus);
        this.assignmentRepository.save(assignment);

        // when
        List<AssignmentDMO> found = this.assignmentRepository.findAllByHubId(foundHub.getId());
        // then
        assertThat(found.size()).isEqualTo(1);


    }

    @Test
    public void whenFindByReservationId_thenReturnOne() {
        HostDMO host = new HostDMO();
        host.setName("nikos");
        host.setEmail("nikos@nikos.gr");
        this.hostRepository.save(host);
        HostDMO foundHost = hostRepository.findByName(host.getName());

        HubDMO hubDMO = new HubDMO();
        hubDMO.setHost(foundHost);
        hubDMO.setName("HUB 1");
        hubDMO.setLocation("location of hub");
        this.hubRepository.save(hubDMO);

        RoomDMO room = new RoomDMO();
        room.setName("Room 1");
        room.setNumberOfBeds(2);
        HubDMO foundHub = hubRepository.findByName(hubDMO.getName());
        room.setHub(foundHub);
        this.roomRepository.save(room);
        RoomDMO foundRoom = this.roomRepository.findByName(room.getName());

        ReservationStatusDMO reservationStatus = new ReservationStatusDMO();
        reservationStatus.setName("FREE");
        this.reservationStatusRepository.save(reservationStatus);
        ReservationStatusDMO foundStatus = this.reservationStatusRepository.findByName("FREE");

        ReservationDMO reservation = new ReservationDMO();
        reservation.setEmail("client@test.gr");
        reservation.setBabies(0);
        reservation.setRoom(foundRoom);
        reservation.setChildren(0);
        reservation.setPersons(2);
        reservation.setStatus(foundStatus);


        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        try {
            date = new Date(formatter.parse("20-10-2023").getTime());
            reservation.setTrueCheckOut(date);
            reservation.setCheckIn(date);
            reservation.setCheckOut(date);

        } catch (Exception e) {
            e.printStackTrace();
        }
        this.reservationRepository.save(reservation);
        ReservationDMO foundReservation = this.reservationRepository.findByEmail(reservation.getEmail());

        ServiceTypeDMO serviceTypeDMO = new ServiceTypeDMO();
        serviceTypeDMO.setName("Room Service");
        this.serviceTypeRepository.save(serviceTypeDMO);
        ServiceTypeDMO foundServiceType = this.serviceTypeRepository.findByName(serviceTypeDMO.getName());

        VehicleTypeDMO vehicleType = new VehicleTypeDMO();
        vehicleType.setName("bike");
        this.vehicleTypeRepository.save(vehicleType);
        VehicleTypeDMO foundVehicleType = this.vehicleTypeRepository.findByName(vehicleType.getName());

        OfferedServiceDMO offeredService = new OfferedServiceDMO();
        offeredService.setName("Service 1");
        offeredService.setServiceType(foundServiceType);
        offeredService.setVehicleType(foundVehicleType);
        this.offeredServiceRepository.save(offeredService);
        OfferedServiceDMO foundService = this.offeredServiceRepository.findByName(offeredService.getName());

        ServiceRequestDMO requestDMO = new ServiceRequestDMO();
        requestDMO.setEmail("test@test.gr");
        requestDMO.setReservation(foundReservation);
        requestDMO.setOfferedService(foundService);
        this.serviceRequestRepository.save(requestDMO);
        ServiceRequestDMO foundServiceRequest = this.serviceRequestRepository.findAllByRoomId(foundRoom.getId()).get(0);
//
//        assertThat(foundReservation.getEmail()).isEqualTo(reservation.getEmail());
        AssignmentStatusDMO assignmentStatusDMO = new AssignmentStatusDMO();
        assignmentStatusDMO.setStatus("OK");
        this.assignmentStatusRepostitory.save(assignmentStatusDMO);
        AssignmentStatusDMO foundAssignmentStatus = this.assignmentStatusRepostitory.findByStatus("OK");

        EmployeeTypeDMO empType = new EmployeeTypeDMO();
        empType.setName("slave");
        this.employeeTypeRepository.save(empType);
        EmployeeTypeDMO foundEmpType = this.employeeTypeRepository.findByName(empType.getName());

        EmployeeDMO employee = new EmployeeDMO();
        employee.setHasVehicle(false);
        employee.setHost(foundHost);
        employee.setEmail("emp@test.gr");
        employee.setPhoneNumber("123");
        employee.setName("test name");
        employee.setType(foundEmpType);
        employee.setVehicleType(foundVehicleType);
        this.employeeRepository.save(employee);
        EmployeeDMO foundEmployee = this.employeeRepository.findByPhoneNumber(employee.getPhoneNumber());

        AssignmentDMO assignment = new AssignmentDMO();
        assignment.setEmployee(employee);
        assignment.setRequest(foundServiceRequest);
        assignment.setStatus(foundAssignmentStatus);
        this.assignmentRepository.save(assignment);

        // when
        List<AssignmentDMO> found = this.assignmentRepository.findAllByReservationId(foundReservation.getId());
        // then
        assertThat(found.size()).isEqualTo(1);


    }

}