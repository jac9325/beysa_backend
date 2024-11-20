package com.beysa.services.UserDomain.Admin;

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
@Table(name = "t_admin")
public class AdminEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_admin")
    private Long idAdmin;

    @ManyToOne
    @JoinColumn(name = "id_staff")
    private Staff staff;

    @Column(name = "branch_manager")
    private String branchManager;

    @Column(name = "type_admin")
    private String typeAdmin;

    @Column(name = "create_ad")
    private LocalDateTime createAd;

    @Column(name = "update_ad")
    private LocalDateTime updateAd;

    @Column(name = "status")
    private Integer status;
}
