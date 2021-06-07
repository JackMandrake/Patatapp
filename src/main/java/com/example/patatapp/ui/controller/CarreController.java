package com.example.patatapp.ui.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.patatapp.bo.Carre;

import com.example.patatapp.service.CarreService;

@Controller
public class CarreController {

	private final CarreService service;
	
	@Autowired
	public CarreController(CarreService service) {
		this.service = service;
	}
	
	@GetMapping("/carre/list")
	public String getAll(Model model) {
		List<Carre> carreList = service.findAll();
		model.addAttribute("carreList", carreList);
		return "carre/carre-list";
	}
	
	@GetMapping("/carre/add")
	public String showCreateCarreForm(Model model) {
		Carre carre= new Carre();
		carre.setSurface(0);
		carre.setExposition("exposition");
		carre.setTypeDeSol("typeDeSol");
		model.addAttribute("carre", carre);
		return "carre/carre-form";
		
	}
	
	@PostMapping("/carre/valider")
    public String create(@Valid Carre carre, BindingResult result) {
        if (result.hasErrors()) {
            return "carre/carre-form";
        } else {
            service.create(carre);
            return "redirect:/carre/list";
        }
    }
	
	@GetMapping("/carre/{id}/delete")
    public String delete(@PathVariable Integer id) {
        service.deleteById(id);
        return "redirect:/carre/list";
    }

    @GetMapping("/carre/{id}/update")
    public String showUpdateCarreForm(@PathVariable Integer id, Model model) {
        Carre carre = service.findById(id);
        model.addAttribute("carre", carre);
        return "carre/update-carre-form";
    }

    @PostMapping("/carre/{id}/valider-update")
    public String update(@PathVariable Integer id, @Valid Carre carre, BindingResult result) {
        if (result.hasErrors()) {
            return "carre/update-carre-form";
        } else {
            carre.setId(id);
            service.update(carre);
            return "redirect:/carre/list";
        }
    }
}
