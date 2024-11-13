package com.beysa.services.UserDomain.Collaborator;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import com.beysa.services.UserDomain.Staff.Staff;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_collaborator")
public class Collaborator {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_collaborator")
    private Long idCollaborator;

    @ManyToOne
    @JoinColumn(name = "id_staff")
    private Staff staff;

    @ManyToOne
    @JoinColumn(name = "level_education")
    private String levelEducation;

    @Column(name = "study_qualification")
    private String studyQualification;

    @Column(name = "create_ad")
    private LocalDateTime createAd;

    @Column(name = "update_ad")
    private LocalDateTime updateAd;

    @Column(name = "status")
    private Integer status;
}
