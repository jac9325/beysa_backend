package com.beysa.services.UserDomain.Expense;

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
@Table(name = "t_expense")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_expense")
    private Long idExpense;

    @Column(name = "amount", precision = 12, scale = 4)
    private BigDecimal amount;

    @Column(name = "reason")
    private String reason;

    @Column(name = "type_expense")
    private String typeExpense;

    @Column(name = "date_expense")
    private LocalDateTime dateExpense;

    @Column(name = "notes")
    private String notes;

    @ManyToOne
    @JoinColumn(name = "id_cash_session")
    private CashSession cashSession;

    @Column(name = "is_manually")
    private Integer isManually;

    @Column(name = "status")
    private Integer status;
}
