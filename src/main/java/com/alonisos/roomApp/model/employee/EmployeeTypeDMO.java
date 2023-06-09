package com.alonisos.roomApp.model.employee;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "EmployeeType")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeTypeDMO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;
    @Column(name = "name")
    private String name;

    // Constructors, getters, and setters
}
