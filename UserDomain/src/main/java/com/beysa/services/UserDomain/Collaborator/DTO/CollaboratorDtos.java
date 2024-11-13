package com.beysa.services.UserDomain.Collaborator.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollaboratorDtos {
    private Long idCollaborator;
    private Long idStaff;               
    private String levelEducation; 
    private String studyQualification;
    private LocalDateTime createAd;
    private LocalDateTime updateAd;
    private Integer status;
}
