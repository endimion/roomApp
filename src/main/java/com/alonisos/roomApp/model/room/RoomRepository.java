package com.alonisos.roomApp.model.room;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RoomRepository extends JpaRepository<RoomDMO, String>,
        JpaSpecificationExecutor<RoomDMO> {

    RoomDMO findByName(String name);
}
