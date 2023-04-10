package com.alonisos.roomApp.model.offeredService;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface ServiceTypeRepository extends JpaRepository<ServiceTypeDMO, String>,
        JpaSpecificationExecutor<ServiceTypeDMO> {

    ServiceTypeDMO findByName(String name);
}