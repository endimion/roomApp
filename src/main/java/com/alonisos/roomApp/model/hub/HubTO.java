package com.alonisos.roomApp.model.hub;

import com.alonisos.roomApp.model.host.HostDMO;
import com.alonisos.roomApp.model.host.HostTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HubTO {

    private Long id;
    private HostTO host;
    private String name;
    private String location;

    // getters and setters
}