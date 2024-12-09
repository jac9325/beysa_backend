package com.beysa.services.UserDomain.Zone;

import com.beysa.services.UserDomain.Zone.DTO.ZoneDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ZoneUtils {
    public ZoneDto convertZoneDto(Zone zone){
        ZoneDto response = new ZoneDto();
        response.setIdZone(zone.getIdZone());
        response.setIdTreatment(zone.getTreatment().getIdTreatment());
        response.setName(zone.getName());
        response.setDescription(zone.getDescription());
        response.setNumberSessions(zone.getNumberSessions());
        response.setPrice(zone.getPrice());
        response.setStatus(zone.getStatus());
        return response;
    }

    public List<ZoneDto> convertListZoneDto(List<Zone> listZone){
        return listZone.stream()
                .map(this::convertZoneDto)
                .collect(Collectors.toList());
    }
}
