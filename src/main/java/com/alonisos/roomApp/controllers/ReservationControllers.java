package com.alonisos.roomApp.controllers;

import com.alonisos.roomApp.service.HubService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController("/reservations")
public class ReservationControllers {

    private final HubService hubService;

    public ReservationControllers(HubService hubService){
        this.hubService = hubService;
    }

    @GetMapping("/get-by-host-mail-and-hub/{param1}/{param2}")
    public ResponseEntity<String> getMyResource(
            @PathVariable("email") String hostEmail,
            @PathVariable("hubId") long hubId) {

        // Your business logic here
//        try{
//            List<Reserthis.hubService.getReservationsPerHubAndHostEmail(hubId,hostEmail);
//        }

        //return ResponseEntity.ok(response);
        return null;
    }

}
