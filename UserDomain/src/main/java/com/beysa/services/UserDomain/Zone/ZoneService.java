package com.beysa.services.UserDomain.Zone;

import com.beysa.services.UserDomain.Zone.DTO.ZoneDto;

import java.util.List;

public interface ZoneService {
    ZoneDto getZoneById(Long idZone);
    List<ZoneDto> getAllZone();
    List<ZoneDto> getZoneByIdTreatment(Long idTreatment);
}
