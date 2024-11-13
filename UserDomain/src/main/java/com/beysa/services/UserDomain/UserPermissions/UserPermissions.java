package com.beysa.services.UserDomain.UserPermissions;

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
@Table(name = "t_user_permissions")

public class UserPermissions{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_user_permissions")
    private long id_user_permissions;
    
    @JoinColumn(name = "id_user")
    private UserEntity user;
            
    @JoinColumn(name = "id_permissions")
    private Permissions permissions;
          
}