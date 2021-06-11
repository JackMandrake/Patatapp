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
        model.addAttribute("title", "Potager");
        model.addAttribute("classPotagerActive", "active");
        return "potager/potager-list";
    }

    @GetMapping("/add")
    public String showCreatePotagerForm(Model model) {
        Potager potager = new Potager();
        potager.setName("nouveau potager");
        potager.setSurface(10);
        model.addAttribute("potager", potager);
        model.addAttribute("title", "Potager");
        model.addAttribute("classPotagerActive", "active");
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
        model.addAttribute("title", "Potager");
        model.addAttribute("classPotagerActive", "active");
        return "potager/update-potager-form";
    }

    @PostMapping("/{id}/valider-update")
    public String update(@PathVariable Integer id, @Valid Potager potager, BindingResult result) {
        if (result.hasErrors()) {
            return "potager/update-potager-form";
        } else {
            Potager potagerFromDb = service.findById(id);
            potager.setId(id);
            potager.setCarreList(potagerFromDb.getCarreList());
            service.update(potager);
            return "redirect:/potager/list";
        }
    }

    @GetMapping("/overview")
    public String overview(Model model) {
        List<Potager> potagerList = service.findAll();
        model.addAttribute("potagerList", potagerList);
        model.addAttribute("potager", null);
        model.addAttribute("title", "Vue d'ensemble");
        model.addAttribute("classOverviewActive", "active");
        return "potager/potager-overview";
    }

    @GetMapping("/{potagerId}/overview")
    public String potagerOverview(@PathVariable Integer potagerId, Model model) {
        List<Potager> potagerList = service.findAll();
        Potager potager = service.findById(potagerId);
        model.addAttribute("potagerList", potagerList);
        model.addAttribute("potager", potager);
        model.addAttribute("title", "Vue d'ensemble");
        model.addAttribute("classOverviewActive", "active");
        return "potager/potager-overview";
    }

}
