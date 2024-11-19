package com.beysa.services.UserDomain.Staff;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.beysa.services.UserDomain.GeographicalLocation.GeographicalLocation;
import com.beysa.services.UserDomain.IdentityDocument.IdentityDocument;
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
@Table(name = "t_staff")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_staff")
    private Long idStaff;

    @Column(name = "name", columnDefinition = "text")
    private String name;

    @Column(name = "last_name", columnDefinition = "text")
    private String lastName;

    @Column(name = "date_birth")
    private LocalDateTime dateOfBirth;

    @Column(name = "gender", length = 1)
    private String gender;

    @ManyToOne
    @JoinColumn(name = "id_identity_document")
    private IdentityDocument identityDocument;

    @Column(name = "document_number", columnDefinition = "text")
    private String documentNumber;

    @Column(name = "mobile_number", columnDefinition = "text")
    private String mobileNumber;

    @Column(name = "email", columnDefinition = "text")
    private String email;

    @Column(name = "address", columnDefinition = "text")
    private String address;

    @Column(name = "image", columnDefinition = "text")
    private String image;

    @Column(name = "date_entry")
    private LocalDateTime dateEntry;

    @ManyToOne
    @JoinColumn(name = "id_geographical_location")
    private GeographicalLocation geographicalLocation;

    @Column(name = "salary", precision = 12, scale = 2)
    private BigDecimal salary;

    @Column(name = "contract_type", columnDefinition = "text")
    private String contractType;

    @Column(name = "status")
    private Integer status;

    @Column(name = "type_staff")
    private StaffEnum typeStaff;

    @ManyToOne
    @JoinColumn(name="id_user")
    private UserEntity user;
}
