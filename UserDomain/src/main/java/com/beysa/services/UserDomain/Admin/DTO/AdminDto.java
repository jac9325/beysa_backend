package com.beysa.services.UserDomain.Admin.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminDto {
    private Long idAdmin;
    private Long idStaff;            
    private String branchManager;
    private String typeAdmin;
    private LocalDateTime createAd;
    private LocalDateTime updateAd;
    private Integer status;
}
