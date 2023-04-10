package com.alonisos.roomApp.model.employee;

import javax.persistence.*;

@Entity
@Table(name = "EmployeeType")
public class EmployeeTypeDMO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Name")
    private String name;

    // Constructors, getters, and setters
}
