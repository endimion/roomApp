package com.alonisos.roomApp.model.hub;

import com.alonisos.roomApp.model.host.HostDMO;
import com.alonisos.roomApp.model.room.RoomDMO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HubRepository  extends JpaRepository<HubDMO, String>,
        JpaSpecificationExecutor<HubDMO> {

    HubDMO findByName(String name);

    List<HubDMO> findByHostId(Long hostId);

    @Query("SELECT r FROM RoomDMO r JOIN r.hub h WHERE h.host.id = :hostId")
    List<RoomDMO> findRoomsByHostId(Long hostId);
}
