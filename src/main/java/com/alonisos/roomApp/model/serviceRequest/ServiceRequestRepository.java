package com.alonisos.roomApp.model.serviceRequest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ServiceRequestRepository extends JpaRepository<ServiceRequestDMO, String>,
        JpaSpecificationExecutor<ServiceRequestDMO> {

    @Query("SELECT sr FROM ServiceRequestDMO sr JOIN sr.reservation rv JOIN rv.room r  WHERE r.id = :roomId")
    List<ServiceRequestDMO> findAllByRoomId(Long roomId);
}
