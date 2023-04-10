package com.alonisos.roomApp.model.host;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface HostRepository extends JpaRepository<HostDMO, String>,
        JpaSpecificationExecutor<HostDMO> {
    HostDMO findByName(String name);

    HostDMO findByEmail(String email);
}