package com.beysa.services.UserDomain.CashRegister;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import com.beysa.services.UserDomain.Collaborator.Collaborator;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_cash_register")
public class CashRegister {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_cash_register")
    private Long idCashRegister;

    @Column(name="name")
    private String name;

    @Column(name = "status")
    private Integer status;

    @Column(name = "create_ad")
    private LocalDateTime createAd;

    @Column(name = "update_ad")
    private LocalDateTime updateAd;

    @ManyToOne
    @JoinColumn(name="id_collaborator")
    private Collaborator idColaborator;

}
