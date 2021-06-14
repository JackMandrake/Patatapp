package com.example.patatapp.ui.controller;


import com.example.patatapp.bo.Carre;
import com.example.patatapp.bo.Error;
import com.example.patatapp.bo.Plante;
import com.example.patatapp.service.BllException;
import com.example.patatapp.service.CarrePlanteService;
import com.example.patatapp.service.PlanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/plante")
public class PlanteController {

    private final PlanteService planteService;
    private final CarrePlanteService carrePlanteService;
    private final Error error;

    @Autowired
    public PlanteController(PlanteService planteService, CarrePlanteService carrePlanteService, Error error) {
        this.planteService = planteService;
        this.carrePlanteService = carrePlanteService;
        this.error = error;
    }

    @GetMapping("/list")
    public String getAll(Model model) {
        List<Plante> planteList = planteService.findAll();
        model.addAttribute("planteList", planteList);
        model.addAttribute("errorMessage", error.getErrorMessage());
        error.setErrorMessage("");
        model.addAttribute("title", "Plante");
        model.addAttribute("classPlanteActive", "active");
        return "plante/plante-list";
    }

    @GetMapping("/add")
    public String showCreatePlanteForm(Model model) {
        Plante plante = new Plante();
        plante.setName("nouvelle plante");
        model.addAttribute("plante", plante);
        model.addAttribute("title", "Plante");
        model.addAttribute("classPlanteActive", "active");
        return "plante/plante-form";
    }

    @PostMapping("/valider")
    public String create(@Valid Plante plante, BindingResult result) {
        if (result.hasErrors()) {
            return "plante/plante-form";
        } else {
            try {
                planteService.create(plante);
            } catch (BllException e) {
                result.addError(new FieldError("plante", "name", e.getMessage()));
                return "plante/plante-form";
            }
            return "redirect:/plante/list";
        }
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Integer id) {
        try {
            planteService.deleteById(id);
            return "redirect:/plante/list";
        } catch (BllException e) {
            error.setErrorMessage(e.getMessage());
            return "redirect:/plante/list";
        }
    }

    @GetMapping("/{id}/update")
    public String showUpdatePlanteForm(@PathVariable Integer id, Model model) {
        Plante plante = planteService.findById(id);
        model.addAttribute("plante", plante);
        model.addAttribute("title", "Plante");
        model.addAttribute("classPlanteActive", "active");
        return "plante/update-plante-form";
    }

    @PostMapping("/{id}/valider-update")
    public String update(@PathVariable Integer id, @Valid Plante plante, BindingResult result) {
        if (result.hasErrors()) {
            return "plante/update-plante-form";
        } else {
            plante.setId(id);
            planteService.update(plante);
            return "redirect:/plante/list";
        }
    }

    @GetMapping("/{id}/localiser")
    public String localiser(@PathVariable Integer id, Model model) {
        List<Carre> carres = carrePlanteService.localiserPlante(id);
        model.addAttribute("carres", carres);
        model.addAttribute("title", "Localiser une plante");
        model.addAttribute("classPlanteActive", "active");
        return "/plante/localiser-plante";
    }

}
