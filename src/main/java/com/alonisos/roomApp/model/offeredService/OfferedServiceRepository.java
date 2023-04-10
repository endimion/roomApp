package com.alonisos.roomApp.model.offeredService;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OfferedServiceRepository extends JpaRepository<OfferedServiceDMO, String>,
        JpaSpecificationExecutor<OfferedServiceDMO> {

    OfferedServiceDMO findByName(String name);
}