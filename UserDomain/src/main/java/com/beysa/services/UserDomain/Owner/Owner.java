package com.beysa.services.UserDomain.Owner;

import com.beysa.services.UserDomain.IdentityDocument.IdentityDocument;
import com.beysa.services.UserDomain.User.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_owner")
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_owner")
    private Long idOwner;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @ManyToOne
    @JoinColumn(name = "id_identity_document")
    private IdentityDocument identityDocument;

    @Column(name = "document_number")
    private String documentNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserEntity user;

    @Column(name = "image")
    private String image;

    @Column(name = "status")
    private Integer status;
}
