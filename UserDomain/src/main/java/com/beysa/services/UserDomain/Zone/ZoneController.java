package com.beysa.services.UserDomain.Zone;

import com.beysa.services.UserDomain.Middlewares.Codes;
import com.beysa.services.UserDomain.Middlewares.Messages;
import com.beysa.services.UserDomain.Middlewares.ResponseUtils;
import com.beysa.services.UserDomain.Zone.DTO.ZoneDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("api/v1/zone")
public class ZoneController {
    @Autowired
    private ZoneService zoneService;

    Codes codes = new Codes();
    Messages messages = new Messages();
    ResponseUtils response = new ResponseUtils();

    @GetMapping("/getZoneById/{id}")
    public ResponseEntity<?> getZoneById(@PathVariable(value = "id") Long id){
        try{
            ZoneDto zone = zoneService.getZoneById(id);
            return response.ok(codes.ok(), messages.ok(), zone, null);
        }catch (Exception e){
            return response.error(codes.error(), messages.error() + e.getMessage(), null);
        }
    }

    @GetMapping("/getAllZone")
    public ResponseEntity<?> getAllZone(){
        try{
            List<ZoneDto> listZone = zoneService.getAllZone();
            return response.ok(codes.ok(), messages.ok(), listZone, null);
        }catch (Exception e){
            return response.error(codes.error(), messages.error() + e.getMessage(), null);
        }
    }

    @GetMapping("/getZoneByIdTreatment/{id}")
    public ResponseEntity<?> getZoneByIdTreatment(@PathVariable(value = "id") Long id){
        try{
            List<ZoneDto> zone = zoneService.getZoneByIdTreatment(id);
            return response.ok(codes.ok(), messages.ok(), zone, null);
        }catch (Exception e){
            return response.error(codes.error(), messages.error() + e.getMessage(), null);
        }
    }
}
