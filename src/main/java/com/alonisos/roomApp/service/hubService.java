package com.alonisos.roomApp.service;

import com.alonisos.roomApp.model.Reservation.ReservationDMO;
import com.alonisos.roomApp.model.hub.HubDMO;
import com.alonisos.roomApp.model.room.RoomDMO;
import com.alonisos.roomApp.model.serviceRequest.ServiceRequestDMO;

import java.util.List;

public interface hubService {

    public void saveHub(HubDMO hub);

    public void deleteHub(HubDMO hub);

    public List<HubDMO> getHubsByHostId(Long hostId);

    public List<RoomDMO> getRoomsOfHub(Long hubId);

    public List<ServiceRequestDMO> getRequestsPerHub(Long hubId);

    public List<ReservationDMO> getReservationsPerHub(Long hubId);

}
