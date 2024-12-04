package com.beysa.services.UserDomain.IdentityDocument;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_identity_document")
public class IdentityDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_identity_document")
    private Long idIdentityDocument;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    private Integer status;

    @Column(name = "symbol")
    private String symbol;

    @Column(name = "max_lenght")
    private Integer max_lenght;
}
