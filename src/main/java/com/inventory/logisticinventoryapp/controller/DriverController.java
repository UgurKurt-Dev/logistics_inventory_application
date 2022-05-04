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
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("drivers")
public class DriverController {

    @Autowired
    DriverRepository driverRepository;

    @GetMapping("/addDriver")
    public String showAddDriverForm(Driver driver) {
        return "drivers/add-driver";
    }

    @PostMapping("/addDriver")
    public String addDriver(@Valid Driver driver, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "drivers/add-driver";
        }

        driverRepository.save(driver);
        return "redirect:/drivers/index";

    }

    @GetMapping("/index")
    public String showDriverList(Model model) {
        model.addAttribute("drivers", driverRepository.findAll());
        return "drivers/index";
    }

    @GetMapping("/")
    public String showIndex(Model model) {
        model.addAttribute("drivers", driverRepository.findAll());
        return "drivers/index";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Driver driver = driverRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid driver Id:" + id));

        model.addAttribute("driver", driver);
        return "drivers/update-driver";
    }

    @PostMapping("/update/{id}")
    public String updateDriver(@PathVariable("id") long id, @Valid Driver driver, BindingResult result, Model model) {
        if (result.hasErrors()) {
            driver.setId(id);
            return "drivers/update-driver";
        }
        driverRepository.save(driver);
        return "redirect:/drivers/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteDriver(@PathVariable("id") long id) {
        Driver driver = driverRepository.findById(id).orElseThrow(
            () -> new IllegalArgumentException("Invalid driver Id:" + id));
        
        driverRepository.delete(driver);
        return "redirect:/drivers/index";
    }
}



