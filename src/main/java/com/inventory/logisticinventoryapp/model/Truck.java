package com.inventory.logisticinventoryapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
public class Truck {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Size(min=8, max = 11)
    private String plateNumber; 

    @NotNull
    private String brand;

    private String model;

    @NotNull
    @Min(1900)
    @Max(2050)
    private Integer productionYear;
    
}
