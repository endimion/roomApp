package com.alonisos.roomApp.model.assignment;

import com.alonisos.roomApp.model.employee.EmployeeDMO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AssignmentRepository extends JpaRepository<AssignmentDMO, String>,
        JpaSpecificationExecutor<AssignmentDMO> {


    @Query("SELECT a FROM AssignmentDMO a JOIN a.request r JOIN r.reservation rv JOIN rv.room rm  WHERE rm.id = :roomId")
    List<AssignmentDMO> findAllByRoomId(Long roomId);

    List<AssignmentDMO> findAllByEmployee(EmployeeDMO employeeDMO);

    //TODO find by hub
    @Query("SELECT a FROM AssignmentDMO a JOIN a.request r JOIN r.reservation rv JOIN rv.room rm JOIN rm.hub h WHERE h.id = :hubId")
    List<AssignmentDMO> findAllByHubId(Long hubId);

    @Query("SELECT a FROM AssignmentDMO a JOIN a.request r JOIN r.reservation rv JOIN rv.room rm JOIN rm.hub hb JOIN hb.host h WHERE h.id = :hostId")
    List<AssignmentDMO> findAllByHost(Long hostId);

    //TODO find by reservation
    @Query("SELECT a FROM AssignmentDMO a JOIN a.request r JOIN r.reservation rv  WHERE rv.id = :reservationId")
    List<AssignmentDMO> findAllByReservationId(Long reservationId);
}
