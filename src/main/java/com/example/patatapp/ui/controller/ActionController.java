package com.example.patatapp.ui.controller;


import com.example.patatapp.bo.Action;
import com.example.patatapp.bo.Carre;
import com.example.patatapp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/action")
public class ActionController {

    private final ActionService actionService;
    private final CarreService carreService;

    @Autowired
    public ActionController(ActionService actionService, CarreService carreService) {
        this.actionService = actionService;
        this.carreService = carreService;
    }

    @GetMapping("/list")
    public String getAll(Model model) {
        List<Action> actions = actionService.findAll();
        model.addAttribute("actions", actions);
        model.addAttribute("title", "Action");
        model.addAttribute("classActionActive", "active");
        return "action/action-list";
    }

    @GetMapping("/add")
    public String showCreateActionForm(Model model) {
        Action action = new Action();
        action.setEvent("nouvelle action");
        action.setDate(LocalDate.now());
        model.addAttribute("action", action);
        fillUpTheModel(model);
        return "action/action-form";
    }

    @PostMapping("/valider")
    public String create(@Valid Action action, BindingResult result, Model model) {
        if (result.hasErrors()) {
            fillUpTheModel(model);
            return "action/action-form";
        } else {
            actionService.save(action);
            return "redirect:/action/list";
        }
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Integer id) {
        actionService.deleteById(id);
        return "redirect:/action/list";
    }

    @GetMapping("/{id}/update")
    public String showUpdateActionForm(@PathVariable Integer id, Model model) {
        Action action = actionService.findById(id);
        model.addAttribute("action", action);
        fillUpTheModel(model);
        return "action/update-action-form";
    }

    @PostMapping("/{id}/valider-update")
    public String update(@PathVariable Integer id, @Valid Action action, BindingResult result, Model model) {
        if (result.hasErrors()) {
            fillUpTheModel(model);
            return "action/update-action-form";
        } else {
            action.setId(id);
            actionService.update(action);
            return "redirect:/action/list";
        }
    }

    private void fillUpTheModel(Model model) {
        List<Carre> carres = carreService.findAll();
        model.addAttribute("carres", carres);
        model.addAttribute("title", "Action");
        model.addAttribute("classActionActive", "active");
    }

}
