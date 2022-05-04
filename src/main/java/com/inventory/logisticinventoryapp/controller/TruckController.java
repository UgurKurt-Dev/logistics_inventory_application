package com.inventory.logisticinventoryapp.controller;

import javax.validation.Valid;

import com.inventory.logisticinventoryapp.model.Truck;
import com.inventory.logisticinventoryapp.repository.TruckRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("trucks")
public class TruckController {

    @Autowired
    TruckRepository truckRepository;

    @GetMapping("/addTruck")
    public String showAddTruckForm(Truck truck) {
        return "trucks/add-truck";
    }

    @PostMapping("/addTruck")
    public String addTruck(@Valid Truck truck, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "trucks/add-truck";
        }

        truckRepository.save(truck);
        return "redirect:/trucks/listTrucks";

    }

    @GetMapping(value = {"/listTrucks","/"})
    public String showTrucksList(Model model) {
        model.addAttribute("trucks", truckRepository.findAll());
        return "trucks/list-trucks";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Truck truck = truckRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid truck Id:" + id));

        model.addAttribute("truck", truck);
        return "trucks/update-truck";
    }

    @PostMapping("/update/{id}")
    public String updateDriver(@PathVariable("id") long id, @Valid Truck truck, BindingResult result, Model model) {
        if (result.hasErrors()) {
            truck.setId(id);
            return "trucks/update-truck";
        }
        truckRepository.save(truck);
        return "redirect:/trucks/listTrucks";
    }

    @GetMapping("/delete/{id}")
    public String deleteDriver(@PathVariable("id") long id) {
        Truck truck = truckRepository.findById(id).orElseThrow(
            () -> new IllegalArgumentException("Invalid driver Id:" + id));
        
        truckRepository.delete(truck);
        return "redirect:/trucks/listTrucks";
    }
    
}
