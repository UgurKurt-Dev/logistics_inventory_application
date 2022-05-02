package com.inventory.logisticinventoryapp.controller;

import javax.validation.Valid;

import com.inventory.logisticinventoryapp.model.Driver;
import com.inventory.logisticinventoryapp.repository.DriverRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DriverController {

    @Autowired
    DriverRepository driverRepository;

    @GetMapping("/addDriver")
    public String showAddDriverForm(Driver driver) {
        return "add-driver";
    }

    @PostMapping("/addDriver")
    public String addDriver(@Valid Driver driver, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "add-driver";
        }

        driverRepository.save(driver);
        return "redirect:/index";

    }

    @GetMapping("/index")
    public String showDriverList(Model model) {
        model.addAttribute("drivers", driverRepository.findAll());
        return "index";
    }

    @GetMapping("/")
    public String showIndex(Model model) {
        model.addAttribute("drivers", driverRepository.findAll());
        return "index";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Driver driver = driverRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid driver Id:" + id));

        model.addAttribute("driver", driver);
        return "update-driver";
    }

    @PostMapping("/update/{id}")
    public String updateDriver(@PathVariable("id") long id, @Valid Driver driver, BindingResult result, Model model) {
        if (result.hasErrors()) {
            driver.setId(id);
            return "update-driver";
        }

        driverRepository.save(driver);
        return "redirect:/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteDriver(@PathVariable("id") long id) {
        Driver driver = driverRepository.findById(id).orElseThrow(
            () -> new IllegalArgumentException("Invalid driver Id:" + id));
        
        driverRepository.delete(driver);
        return "redirect:/index";
    }

}
