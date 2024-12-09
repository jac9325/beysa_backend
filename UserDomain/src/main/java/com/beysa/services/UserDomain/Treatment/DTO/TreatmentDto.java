package com.beysa.services.UserDomain.Treatment.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreatmentDto {
    private Long idTreatment;
    private String name;
    private String description;
    private LocalDateTime createAd;
    private Long idClinic;
    private Integer status;
}
