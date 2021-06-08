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
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/plante")
public class PlanteController {

    private final PlanteService service;

    @Autowired
    public PlanteController(PlanteService service) {
        this.service = service;
        Plante plante = new Plante();
        plante.setName("Patat");
        this.service.create(plante);
        plante = new Plante();
        plante.setName("Poireaux");
        this.service.create(plante);
        plante = new Plante();
        plante.setName("Choux");
        this.service.create(plante);
    }

    @GetMapping("/list")
    public String getAll(Model model) {
        List<Plante> planteList = service.findAll();
        model.addAttribute("planteList", planteList);
        return "plante/plante-list";
    }

    @GetMapping("/add")
    public String showCreatePlanteForm(Model model) {
        Plante plante = new Plante();
        plante.setName("nouvelle plante");
        model.addAttribute("plante", plante);
        return "plante/plante-form";
    }

    @PostMapping("/valider")
    public String create(@Valid Plante plante, BindingResult result) {
        if (result.hasErrors()) {
            return "plante/plante-form";
        } else {
            service.create(plante);
            return "redirect:/plante/list";
        }
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Integer id) {
        service.deleteById(id);
        return "redirect:/plante/list";
    }

    @GetMapping("/{id}/update")
    public String showUpdatePlanteForm(@PathVariable Integer id, Model model) {
        Plante plante = service.findById(id);
        model.addAttribute("plante", plante);
        return "plante/update-plante-form";
    }

    @PostMapping("/{id}/valider-update")
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
