package com.alonisos.roomApp.jpaTests;

import com.alonisos.roomApp.model.Reservation.ReservationRepository;
import com.alonisos.roomApp.model.Reservation.ReservationStatusRepository;
import com.alonisos.roomApp.model.assignment.AssignmentRepository;
import com.alonisos.roomApp.model.assignment.AssignmentStatusRepostitory;
import com.alonisos.roomApp.model.employee.EmployeeDMO;
import com.alonisos.roomApp.model.employee.EmployeeRepository;
import com.alonisos.roomApp.model.employee.EmployeeTypeDMO;
import com.alonisos.roomApp.model.employee.EmployeeTypeRepository;
import com.alonisos.roomApp.model.host.HostDMO;
import com.alonisos.roomApp.model.host.HostRepository;
import com.alonisos.roomApp.model.hub.HubRepository;
import com.alonisos.roomApp.model.offeredService.OfferedServiceRepository;
import com.alonisos.roomApp.model.offeredService.ServiceTypeRepository;
import com.alonisos.roomApp.model.room.RoomRepository;
import com.alonisos.roomApp.model.roomService.RoomServiceRepository;
import com.alonisos.roomApp.model.serviceRequest.ServiceRequestRepository;
import com.alonisos.roomApp.model.vehicle.VehicleTypeDMO;
import com.alonisos.roomApp.model.vehicle.VehicleTypeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EmployeeDBIntegrationTests extends  DatabaseTest{
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

}
