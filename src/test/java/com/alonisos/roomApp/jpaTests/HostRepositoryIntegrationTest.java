package com.alonisos.roomApp.jpaTests;

import com.alonisos.roomApp.model.host.HostDMO;
import com.alonisos.roomApp.model.host.HostRepository;
import com.alonisos.roomApp.model.hub.HubDMO;
import com.alonisos.roomApp.model.hub.HubRepository;
import com.alonisos.roomApp.model.room.RoomDMO;
import com.alonisos.roomApp.model.room.RoomRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class HostRepositoryIntegrationTest extends DatabaseTest {


    @Autowired
    private HostRepository hostRepository;

    @Autowired
    private HubRepository hubRepository;

    @Autowired
    private RoomRepository roomRepository;



    @Test
    public void whenFindByName_thenReturnHost() {
        // given
        HostDMO host = new HostDMO();
        host.setName("nikos");
        host.setEmail("nikos@nikos.gr");
        this.hostRepository.save(host);

        // when
        HostDMO found = hostRepository.findByName(host.getName());

        // then
        assertThat(found.getName())
                .isEqualTo(host.getName());
    }

    @Test
    public void whenFindByName_thenReturnHub() {
        HostDMO host = new HostDMO();
        host.setName("nikos");
        host.setEmail("nikos@nikos.gr");
        this.hostRepository.save(host);
        HostDMO foundHost = hostRepository.findByName(host.getName());

        HubDMO hubDMO = new HubDMO();
        hubDMO.setHost(foundHost);
        hubDMO.setName("hubName");
        hubDMO.setLocation("location of hub");
        this.hubRepository.save(hubDMO);
        // when
        HubDMO found = hubRepository.findByName(hubDMO.getName());
        // then
        assertThat(found.getName())
                .isEqualTo(hubDMO.getName());
    }

    @Test
    public void whenFindByName_thenRoom() {
        HostDMO host = new HostDMO();
        host.setName("nikos");
        host.setEmail("nikos@nikos.gr");
        this.hostRepository.save(host);
        HostDMO foundHost = hostRepository.findByName(host.getName());

        HubDMO hubDMO = new HubDMO();
        hubDMO.setHost(foundHost);
        hubDMO.setName("HUB 1");
        hubDMO.setLocation("location of hub");
        this.hubRepository.save(hubDMO);

        RoomDMO room = new RoomDMO();
        room.setName("Room 1");
        room.setNumberOfBeds(2);
        HubDMO foundHub = hubRepository.findByName(hubDMO.getName());
        room.setHub(foundHub);
        this.roomRepository.save(room);
        // when
        RoomDMO found = this.roomRepository.findByName(room.getName());
        // then
        assertThat(found.getName())
                .isEqualTo(room.getName());

        assertThat(found.getHub().getName()).isEqualTo(foundHub.getName());
        assertThat(found.getHub().getHost().getName()).isEqualTo(host.getName());

    }


}