package com.example.patatapp.ui.controller;


import com.example.patatapp.bo.Action;
import com.example.patatapp.bo.Carre;
import com.example.patatapp.bo.Potager;
import com.example.patatapp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/action")
public class ActionController {

    private final ActionService actionService;
    private final PotagerService potagerService;
    private final CarreService carreService;

    @Autowired
    public ActionController(ActionService actionService, PotagerService potagerService, CarreService carreService) {
        this.actionService = actionService;
        this.potagerService = potagerService;
        this.carreService = carreService;
    }

//    @GetMapping("/list")
//    public String getAll(Model model) {
//        List<Plante> planteList = planteService.findAll();
//        model.addAttribute("planteList", planteList);
//        model.addAttribute("title", "Plante");
//        model.addAttribute("classPlanteActive", "active");
//        return "plante/plante-list";
//    }

    @GetMapping("/add")
    public String showCreateActionForm(Model model) {
        Action action = new Action();
        action.setEvent("nouvelle action");
        action.setPotagerChosen(true);
        action.setDate(LocalDate.now());
        model.addAttribute("action", action);
        List<Potager> potagers = potagerService.findAll();
        model.addAttribute("potagers", potagers);
        List<Carre> carres = carreService.findAll();
        model.addAttribute("carres", carres);
        model.addAttribute("title", "Action");
        model.addAttribute("classActionActive", "active");
        return "action/action-form";
    }

    @PostMapping("/valider")
    public String create(@Valid Action action, BindingResult result) {
        if (result.hasErrors()) {
            return "action/action-form";
        } else {
            System.out.println(action);
            return "redirect:/action/add";
        }
    }

//    @GetMapping("/{id}/delete")
//    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
//        try {
//            planteService.deleteById(id);
//            return "redirect:/plante/list";
//        } catch (BllException e) {
////            redirectAttributes.addAttribute("errorMessage", e.getMessage());
//            return "redirect:/plante/list?error";
//        }
//    }
//
//    @GetMapping("/{id}/update")
//    public String showUpdatePlanteForm(@PathVariable Integer id, Model model) {
//        Plante plante = planteService.findById(id);
//        model.addAttribute("plante", plante);
//        model.addAttribute("title", "Plante");
//        model.addAttribute("classPlanteActive", "active");
//        return "plante/update-plante-form";
//    }
//
//    @PostMapping("/{id}/valider-update")
//    public String update(@PathVariable Integer id, @Valid Plante plante, BindingResult result) {
//        if (result.hasErrors()) {
//            return "plante/update-plante-form";
//        } else {
//            plante.setId(id);
//            planteService.update(plante);
//            return "redirect:/plante/list";
//        }
//    }
//
//    @GetMapping("/{id}/localiser")
//    public String localiser(@PathVariable Integer id, Model model) {
//        List<Carre> carres = carrePlanteService.localiserPlante(id);
//        model.addAttribute("carres", carres);
//        model.addAttribute("title", "Localiser une plante");
//        model.addAttribute("classPlanteActive", "active");
//        return "/plante/localiser-plante";
//    }

}
