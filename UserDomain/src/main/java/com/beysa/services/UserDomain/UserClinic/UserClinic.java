package com.beysa.services.UserDomain.UserClinic;

import com.beysa.services.UserDomain.Clinic.Clinic;
import com.beysa.services.UserDomain.User.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_user_clinic")
public class UserClinic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_user_clinic")
    private Long idUserClinic;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "id_clinic")
    private Clinic clinic;

    @Column(name = "status")
    private Integer status;
}
