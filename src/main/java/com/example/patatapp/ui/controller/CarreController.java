package com.example.patatapp.ui.controller;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import com.example.patatapp.bo.Potager;
import com.example.patatapp.service.PotagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.patatapp.bo.Carre;

import com.example.patatapp.service.CarreService;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/potager/{potagerId}/carre")
public class CarreController {

    private final CarreService carreService;
    private final PotagerService potagerService;
	
	@Autowired
	public CarreController(CarreService carreService, PotagerService potagerService) {
		this.carreService = carreService;
        this.potagerService = potagerService;
        init();
    }

    public void init() {
        Potager potager = new Potager();
        potager.setName("Mon potager");
        Potager createdPotager = this.potagerService.create(potager);
        Carre carre = new Carre();
        carre.setSurface(10);
        carre.setExposition("sud");
        carre.setTypeDeSol("terreu");
        this.carreService.create(createdPotager, carre);
    }

    @GetMapping("/list")
	public String getAll(@PathVariable Integer potagerId, Model model) {
		List<Carre> carreList = carreService.findAll(potagerId);
		model.addAttribute("carreList", carreList);
        model.addAttribute("potagerId", potagerId);
        model.addAttribute("potagerName", potagerService.findById(potagerId).getName());
		return "carre/carre-list";
	}
	
	@GetMapping("/add")
	public String showCreateCarreForm(@PathVariable Integer potagerId, Model model) {
		Carre carre= new Carre();
		carre.setSurface(0);
		carre.setExposition("exposition");
		carre.setTypeDeSol("typeDeSol");
        model.addAttribute("carre", carre);
        model.addAttribute("potagerId", potagerId);
		return "carre/carre-form";
		
	}
	
	@PostMapping("/valider")
    public String create(@PathVariable Integer potagerId, @Valid Carre carre, BindingResult result) {
        if (result.hasErrors()) {
            return "carre/carre-form";
        } else {
            Potager potager = potagerService.findById(potagerId);
            carreService.create(potager, carre);
            return "redirect:/potager/" + potagerId + "/carre/list";
        }
    }
	
	@GetMapping("/{id}/delete")
    public String delete(@PathVariable Integer id) {
        carreService.deleteById(id);
        return "redirect:/carre/list";
    }

    @GetMapping("/{id}/update")
    public String showUpdateCarreForm(@PathVariable Integer id, Model model) {
        Carre carre = carreService.findById(id);
        model.addAttribute("carre", carre);
        return "carre/update-carre-form";
    }

    @PostMapping("/{id}/valider-update")
    public String update(@PathVariable Integer id, @Valid Carre carre, BindingResult result) {
        if (result.hasErrors()) {
            return "carre/update-carre-form";
        } else {
            carre.setId(id);
            carreService.update(carre);
            return "redirect:/carre/list";
        }
    }
}
