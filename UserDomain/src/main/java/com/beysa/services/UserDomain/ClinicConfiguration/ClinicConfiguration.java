package com.beysa.services.UserDomain.ClinicConfiguration;

import com.beysa.services.UserDomain.Clinic.Clinic;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_clinic_configuration")
public class ClinicConfiguration {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_clinic_configuration")
    private Long idClinicConfiguration;

    @Column(name = "star_day")
    private String starDay;

    @Column(name = "decimal_number")
    private Integer decimalNumber;

    @Column(name = "history_number")
    private String historyNumber;

    @Column(name = "pagination_number")
    private Integer paginationNumber;

    @Column(name = "pre_invoice_number")
    private String preInvoiceNumber;

    @ManyToOne
    @JoinColumn(name = "id_clinic")
    private Clinic clinic;

    @Column(name = "status")
    private Integer status;
}
