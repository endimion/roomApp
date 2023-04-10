package com.alonisos.roomApp.model.hub;

import com.alonisos.roomApp.model.host.HostDMO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "Hub")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HubDMO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "host_id", nullable = false)
    private HostDMO host;

    @Column(name = "Name", nullable = false, length = 50)
    private String name;

    @Column(name = "Location", nullable = false, length = 200)
    private String location;

    // getters and setters
}