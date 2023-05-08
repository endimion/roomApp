package com.alonisos.roomApp.service.impl;

import com.alonisos.roomApp.model.Reservation.ReservationDMO;
import com.alonisos.roomApp.model.Reservation.ReservationRepository;
import com.alonisos.roomApp.model.host.HostDMO;
import com.alonisos.roomApp.model.host.HostRepository;
import com.alonisos.roomApp.model.hub.HubDMO;
import com.alonisos.roomApp.model.hub.HubRepository;
import com.alonisos.roomApp.model.room.RoomDMO;
import com.alonisos.roomApp.model.serviceRequest.ServiceRequestDMO;
import com.alonisos.roomApp.service.HubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HubServiceImpl implements HubService {

    private final HubRepository hubRepository;
    private final HostRepository hostRepository;
    private final ReservationRepository reservationRepository;

    @Autowired
    public HubServiceImpl(HubRepository hubRepository, HostRepository hostRepository, ReservationRepository reservationRepository) {
        this.hubRepository = hubRepository;
        this.hostRepository = hostRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    @Transactional
    public void saveHub(HubDMO hub) {
        this.hubRepository.save(hub);
        this.hubRepository.save(hub);
    }

    @Override
    @Transactional
    public void deleteHub(HubDMO hub) {
        this.hubRepository.delete(hub);
    }

    @Override
    public List<HubDMO> getHubsByHostId(Long hostId) {
        return this.hubRepository.findByHostId(hostId);
    }

    @Override
    public List<RoomDMO> getRoomsOfHub(Long hubId) {
        //TODO
        return null;
    }

    @Override
    public List<ServiceRequestDMO> getRequestsPerHub(Long hubId) {
        //TODO
        return null;
    }

    @Override
    public List<ReservationDMO> getReservationsPerHubAndHostEmail(Long hubId, String hostEmail) {
        HostDMO host = this.hostRepository.findByEmail(hostEmail);
        if (host != null) {
            return this.reservationRepository.findAllByHostId(host.getId()).stream().filter(reservationDMO -> {
                return reservationDMO.getRoom().getHub().getId().equals(hubId);
            }).collect(Collectors.toList());
        }
        return null;
    }
}
