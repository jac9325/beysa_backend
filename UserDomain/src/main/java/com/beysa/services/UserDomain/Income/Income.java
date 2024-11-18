package com.beysa.services.UserDomain.Income;

import com.beysa.services.UserDomain.CashSession.CashSession;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_income")
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_income")
    private Long idIncome;

    @Column(name = "amount", precision = 12, scale = 4)
    private BigDecimal amount;

    @Column(name = "reason")
    private String reason;

    @Column(name = "type_income")
    private String typeIncome;

    @Column(name = "date_income")
    private LocalDateTime dateIncome;

    @Column(name = "notes")
    private String notes;

    @Column(name = "is_manually")
    private Integer isManually;

    @ManyToOne
    @JoinColumn(name = "id_cash_session")
    private CashSession cashSession;

    @Column(name = "status")
    private Integer status;
}
