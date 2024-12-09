package com.beysa.services.UserDomain.Zone.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZoneDto {
    private Long idZone;
    private Long idTreatment;
    private String name;
    private String description;
    private Integer numberSessions;
    private BigDecimal price;
    private Integer status;
}
