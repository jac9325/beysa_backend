package com.beysa.services.UserDomain.CashClosing;

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
@Table(name = "t_cash_closing")
public class CashClosing {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCashClosing;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "notes")
    private String notes;

    @Column(name = "initial_amount", precision = 12, scale = 4)
    private BigDecimal initialAmount;

    @Column(name = "closing_amount", precision = 12, scale = 4)
    private BigDecimal closingAmount;

    @Column(name = "closing_time")
    private String closingTime;

    @Column(name = "shortage", precision = 12, scale = 4)
    private BigDecimal shortage;

    @Column(name = "surplus", precision = 12, scale = 4)
    private BigDecimal surplus;

    @Column(name = "physical_total", precision = 12, scale = 4)
    private BigDecimal physicalTotal;

    @Column(name = "real_amount", precision = 12, scale = 4)
    private BigDecimal realAmount;

    @Column(name = "force_save")
    private Integer forceSave;

    @ManyToOne
    @JoinColumn(name = "id_cash_session")
    private CashSession cashSession;

    @Column(name = "status")
    private Integer status;
}
