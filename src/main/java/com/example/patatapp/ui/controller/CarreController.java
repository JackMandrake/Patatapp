package com.example.patatapp.ui.controller;

import java.util.List;

import javax.validation.Valid;

import com.example.patatapp.bo.Potager;
import com.example.patatapp.service.BllException;
import com.example.patatapp.service.PotagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
    }

    @GetMapping("/list")
	public String getAll(@PathVariable Integer potagerId, Model model) {
		List<Carre> carreList = carreService.findAllByPotagerId(potagerId);
		model.addAttribute("carreList", carreList);
        model.addAttribute("potagerId", potagerId);
        model.addAttribute("potagerName", potagerService.findById(potagerId).getName());
        model.addAttribute("title", "Carré");
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
        model.addAttribute("title", "Carré");
		return "carre/carre-form";
		
	}
	
	@PostMapping("/valider")
    public String create(@PathVariable Integer potagerId, @Valid Carre carre, BindingResult result) {
        if (result.hasErrors()) {
            return "carre/carre-form";
        } else {
            Potager potager = potagerService.findById(potagerId);
            try {
                carreService.create(potager, carre);
            } catch (BllException e) {
                result.addError(new FieldError("carre", "surface", e.getMessage()));
                return "carre/carre-form";
            }
            return "redirect:/potager/" + potagerId + "/carre/list";
        }
    }
	
	@GetMapping("/{id}/delete")
    public String delete(@PathVariable Integer potagerId, @PathVariable Integer id) {
        carreService.deleteById(id);
        return "redirect:/potager/" + potagerId + "/carre/list";
    }

    @GetMapping("/{id}/update")
    public String showUpdateCarreForm(@PathVariable Integer potagerId, @PathVariable Integer id, Model model) {
        Carre carre = carreService.findById(id);
        model.addAttribute("carre", carre);
        model.addAttribute("potagerId", potagerId);
        model.addAttribute("title", "Carré");
        return "carre/update-carre-form";
    }

    @PostMapping("/{id}/valider-update")
    public String update(@PathVariable Integer potagerId, @PathVariable Integer id, @Valid Carre carre, BindingResult result) {
        if (result.hasErrors()) {
            return "carre/update-carre-form";
        } else {
            carre.setId(id);
            Potager potager = potagerService.findById(potagerId);
            carre.setPotager(potager);
            try {
                carreService.update(carre);
            } catch (BllException e) {
                result.addError(new FieldError("carre", "surface", e.getMessage()));
                return "carre/update-carre-form";
            }
            return "redirect:/potager/" + potagerId + "/carre/list";
        }
    }

}
