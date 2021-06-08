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
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/potager")
public class PotagerController {

    private final PotagerService service;

    @Autowired
    public PotagerController(PotagerService service) {
        this.service = service;
    }

    @GetMapping("/list")
    public String getAll(Model model) {
        List<Potager> potagerList = service.findAll();
        model.addAttribute("potagerList", potagerList);
        return "potager/potager-list";
    }

    @GetMapping("/add")
    public String showCreatePotagerForm(Model model) {
        Potager potager = new Potager();
        potager.setName("nouveau potager");
        model.addAttribute("potager", potager);
        return "potager/potager-form";
    }

    @PostMapping("/valider")
    public String create(@Valid Potager potager, BindingResult result) {
        if (result.hasErrors()) {
            return "potager/potager-form";
        } else {
            service.create(potager);
            return "redirect:/potager/list";
        }
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Integer id) {
        service.deleteById(id);
        return "redirect:/potager/list";
    }

    @GetMapping("/{id}/update")
    public String showUpdatePotagerForm(@PathVariable Integer id, Model model) {
        Potager potager = service.findById(id);
        model.addAttribute("potager", potager);
        return "potager/update-potager-form";
    }

    @PostMapping("/{id}/valider-update")
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
