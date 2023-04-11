package com.alonisos.roomApp.model.assignment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AssignmentStatusRepostitory extends JpaRepository<AssignmentStatusDMO, String>,
        JpaSpecificationExecutor<AssignmentStatusDMO> {
    AssignmentStatusDMO findByStatus(String status);

}