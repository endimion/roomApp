package com.alonisos.roomApp.model.Reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ReservationStatusRepository  extends JpaRepository<ReservationStatusDMO, String>,
        JpaSpecificationExecutor<ReservationStatusDMO> {

    ReservationStatusDMO findByName(String name);
}
