package com.beysa.services.UserDomain.User;

import java.util.List;

import com.beysa.services.UserDomain.Rol.Rol;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "t_user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_user")
    private Long id_user;

    @NotBlank
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "status")
    private Integer status;

    @NotBlank
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "is_main")
    private Integer is_main;

    @Column(name = "type_user")
    private UserEnum type_user;

    @Column(name = "email")
    private String email;
    
    @ManyToMany
    @JoinTable(
        name="t_rol_user",
        joinColumns = @JoinColumn(name="id_user"),
        inverseJoinColumns = @JoinColumn(name="id_rol")
    )
    private List<Rol> roles;
}
