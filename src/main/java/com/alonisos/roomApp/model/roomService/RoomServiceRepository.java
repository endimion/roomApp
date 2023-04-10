package com.alonisos.roomApp.model.roomService;

import com.alonisos.roomApp.model.offeredService.OfferedServiceDMO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoomServiceRepository extends JpaRepository<RoomServiceDMO, String>,
        JpaSpecificationExecutor<RoomServiceDMO> {


    @Query("SELECT r FROM RoomServiceDMO r JOIN r.room rm  WHERE rm.id = :roomId")
    List<RoomServiceDMO> findByRoomId(Long roomId);

}
