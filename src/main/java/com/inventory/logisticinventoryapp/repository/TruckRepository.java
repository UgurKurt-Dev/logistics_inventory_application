package com.inventory.logisticinventoryapp.repository;

import com.inventory.logisticinventoryapp.model.Truck;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TruckRepository extends CrudRepository<Truck, Long>{
    
}
