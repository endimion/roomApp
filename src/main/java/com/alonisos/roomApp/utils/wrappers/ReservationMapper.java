package com.alonisos.roomApp.utils.wrappers;

import com.alonisos.roomApp.model.Reservation.ReservationDMO;
import com.alonisos.roomApp.model.Reservation.ReservationTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    ReservationMapper INSTANCE = Mappers.getMapper(ReservationMapper.class);

    ReservationTO toTO(ReservationDMO reservationDMO);

    ReservationDMO toEntity(ReservationTO reservationTO);


}
