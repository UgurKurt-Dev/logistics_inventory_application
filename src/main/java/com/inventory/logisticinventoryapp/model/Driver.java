package com.inventory.logisticinventoryapp.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Entity
@Data
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Email is mandatory")
    private String email;

    private String phoneNumber;

    @Min(1900)
    @Max(2050)
    private Integer dateOfBirth;

    private Date employmentDate;

    /*@ElementCollection
    @CollectionTable(name = "DriverLicences", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "driverLicences")
    private List<DriverLicences> driverLicenses;*/

    private DriverLicences driverLicences;

}
