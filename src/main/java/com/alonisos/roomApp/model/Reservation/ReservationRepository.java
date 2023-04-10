package com.alonisos.roomApp.model.Reservation;

import com.alonisos.roomApp.model.room.RoomDMO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReservationRepository extends JpaRepository<ReservationDMO, String>,
        JpaSpecificationExecutor<ReservationDMO> {

    ReservationDMO findByEmail(String email);

    ReservationDMO findByRoom(RoomDMO room);

    //TBD find by hub
    @Query("SELECT r FROM ReservationDMO r JOIN r.room rm JOIN rm.hub h  WHERE h.id = :hubId")
    List<ReservationDMO> findAllByHubId(Long hubId);

    //TBD find by host
    @Query("SELECT r FROM ReservationDMO r JOIN r.room rm JOIN rm.hub h JOIN h.host ho WHERE ho.id = :hostId")
    List<ReservationDMO> findAllByHostId(Long hostId);

    @Query("SELECT r FROM ReservationDMO r JOIN r.room rm JOIN rm.hub h JOIN h.host ho ON ho.id = :hostId AND rm.id = :roomId")
    List<ReservationDMO> findAllByHostIdAndRoomId(Long hostId, Long roomId);
}
