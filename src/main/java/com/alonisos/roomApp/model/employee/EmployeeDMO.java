package com.alonisos.roomApp.model.employee;

import com.alonisos.roomApp.model.Reservation.ReservationStatusDMO;

import javax.persistence.*;

@Entity
@Table(name = "Employee")
public class EmployeeDMO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Email", nullable = false, unique = true)
    private String email;

    @Column(name = "HostId", nullable = false)
    private String hostId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Type", nullable = false)
    private EmployeeTypeDMO type;

    @Column(name = "PhoneNumber", nullable = false)
    private String phoneNumber;

    @Column(name = "HasVehicle", nullable = false)
    private Boolean hasVehicle;

    @Column(name = "VehicleType", nullable = false)
    private Integer vehicleType;

    // Constructors, getters, and setters
}