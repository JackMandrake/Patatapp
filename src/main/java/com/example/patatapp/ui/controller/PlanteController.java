package com.example.patatapp.ui.controller;


import com.example.patatapp.bo.Plante;
import com.example.patatapp.service.PlanteService;
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
public class PlanteController {

    private final PlanteService service;

    @Autowired
    public PlanteController(PlanteService service) {
        this.service = service;
    }

    @GetMapping("/plante/list")
    public String getAll(Model model) {
        List<Plante> planteList = service.findAll();
        model.addAttribute("planteList", planteList);
        return "plante/plante-list";
    }

    @GetMapping("/plante/add")
    public String showCreatePlanteForm(Model model) {
        Plante plante = new Plante();
        plante.setName("nouvelle plante");
        model.addAttribute("plante", plante);
        return "plante/plante-form";
    }

    @PostMapping("/plante/valider")
    public String create(@Valid Plante plante, BindingResult result) {
        if (result.hasErrors()) {
            return "plante/plante-form";
        } else {
            service.create(plante);
            return "redirect:/plante/list";
        }
    }

    @GetMapping("/plante/{id}/delete")
    public String delete(@PathVariable Integer id) {
        service.deleteById(id);
        return "redirect:/plante/list";
    }

    @GetMapping("/plante/{id}/update")
    public String showUpdatePlanteForm(@PathVariable Integer id, Model model) {
        Plante plante = service.findById(id);
        model.addAttribute("plante", plante);
        return "plante/update-plante-form";
    }

    @PostMapping("/plante/{id}/valider-update")
    public String update(@PathVariable Integer id, @Valid Plante plante, BindingResult result) {
        if (result.hasErrors()) {
            return "plante/update-plante-form";
        } else {
            plante.setId(id);
            service.update(plante);
            return "redirect:/plante/list";
        }
    }



}
