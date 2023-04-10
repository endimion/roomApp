package com.alonisos.roomApp.model.hub;

import com.alonisos.roomApp.model.host.HostDMO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface HubRepository  extends JpaRepository<HubDMO, String>,
        JpaSpecificationExecutor<HubDMO> {

    HubDMO findByName(String name);
}
