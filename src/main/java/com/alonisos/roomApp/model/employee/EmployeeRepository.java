package com.alonisos.roomApp.model.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<EmployeeDMO, String>,
        JpaSpecificationExecutor<EmployeeDMO> {

    EmployeeDMO findByName(String name);
    EmployeeDMO findByPhoneNumber(String phoneNumber);

    @Query("SELECT e FROM EmployeeDMO e JOIN e.host h  WHERE h.id = :hostId")
    List<EmployeeDMO> findAllEmployeesByHostId(Long hostId);
}
