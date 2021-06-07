package com.example.patatapp.ui.controller;

import com.example.patatapp.bo.Potager;
import com.example.patatapp.service.PotagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class PotagerController {

    private final PotagerService service;

    @Autowired
    public PotagerController(PotagerService service) {
        this.service = service;
    }

    @GetMapping("/potager/list")
    public String getAll(Model model) {
        List<Potager> potagerList = service.findAll();
        model.addAttribute("potagerList", potagerList);
        return "potager/potager-list";
    }

    @GetMapping("/potager/add")
    public String showCreatePotagerForm(Model model) {
        Potager potager = new Potager();
        potager.setName("nouveau potager");
        model.addAttribute("potager", potager);
        return "potager/potager-form";
    }

    @PostMapping("/potager/valider")
    public String create(@Valid Potager potager, BindingResult result) {
        if (result.hasErrors()) {
            return "potager/potager-form";
        } else {
            service.create(potager);
            return "redirect:/potager/list";
        }
    }

    @GetMapping("/potager/{id}/delete")
    public String delete(@PathVariable Integer id) {
        service.deleteById(id);
        return "redirect:/potager/list";
    }

    @GetMapping("/potager/{id}/update")
    public String showUpdatePotagerForm(@PathVariable Integer id, Model model) {
        Potager potager = service.findById(id);
        model.addAttribute("potager", potager);
        return "potager/update-potager-form";
    }

    @PostMapping("/potager/{id}/valider-update")
    public String update(@PathVariable Integer id, @Valid Potager potager, BindingResult result) {
        if (result.hasErrors()) {
            return "potager/update-potager-form";
        } else {
            potager.setId(id);
            service.update(potager);
            return "redirect:/potager/list";
        }
    }



}
