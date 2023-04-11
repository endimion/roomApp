package com.alonisos.roomApp.model.employee;

import com.alonisos.roomApp.model.host.HostDMO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EmployeeTypeRepository extends JpaRepository<EmployeeTypeDMO, String>,
        JpaSpecificationExecutor<EmployeeTypeDMO> {

    EmployeeTypeDMO findByName(String name);
}
