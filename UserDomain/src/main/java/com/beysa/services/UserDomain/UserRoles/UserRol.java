package com.beysa.services.UserDomain.UserRoles;

import com.beysa.services.UserDomain.Rol.Rol;
import com.beysa.services.UserDomain.User.UserEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_rol_user")
public class UserRol {
     @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_rol_user")
    private long id_rol_user;
    
    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "id_rol")
    private Rol rol;
}


