package com.beysa.services.UserDomain.CashSession;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.beysa.services.UserDomain.CashRegister.CashRegister;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_cash_session")
public class CashSession {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_cash_session")
    private Long idCashSession;

    @Column(name="name")
    private String name;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime EndDate;

    @Column(name="initial_amount")
    private BigDecimal initialAmount;

    @ManyToOne
    @JoinColumn(name = "id_cash_register")
    private CashRegister cashRegister;

    @Column(name = "status")
    private Integer status;

    @Column(name = "notes")
    private String notes;
}
