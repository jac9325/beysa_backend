package com.beysa.services.UserDomain.Zone;

import com.beysa.services.UserDomain.Zone.DTO.ZoneDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ZoneServiceImpl implements ZoneService{
    
    private final ZoneRepository zoneRepository;
    private final ZoneUtils zoneUtils;

    @Transactional(readOnly = true)
    @Override
    public ZoneDto getZoneById(Long idZone){
        return zoneRepository.findById(idZone)
                .map(zoneUtils::convertZoneDto)
                .orElseThrow(() -> new RuntimeException("Zone no encontrado por el id: " + idZone));
    }

    @Transactional(readOnly = true)
    @Override
    public List<ZoneDto> getAllZone(){
        List<Zone> listZone = zoneRepository.findAll();
        if(listZone.isEmpty()){
            throw new RuntimeException("No se encontraron registros de Zone en la base de datos.");
        }
        return zoneUtils.convertListZoneDto(listZone);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ZoneDto> getZoneByIdTreatment(Long idTreatment){
        List<Zone> zone = zoneRepository.findByIdTreatment(idTreatment);
        if (zone.isEmpty()) {
            throw new RuntimeException("No se encontraron registros de Zona en la base de datos.");
        }
        return zoneUtils.convertListZoneDto(zone);
    }
}
