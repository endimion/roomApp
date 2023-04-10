package com.alonisos.roomApp.model.vehicle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface VehicleTypeRepository extends JpaRepository<VehicleTypeDMO, String>,
        JpaSpecificationExecutor<VehicleTypeDMO>{

    VehicleTypeDMO findByName(String name);
}