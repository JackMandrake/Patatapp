package com.example.patatapp.ui.controller;

import com.example.patatapp.bo.Carre;
import com.example.patatapp.bo.CarrePlante;
import com.example.patatapp.bo.Plante;
import com.example.patatapp.service.CarrePlanteService;
import com.example.patatapp.service.CarreService;
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
@RequestMapping("/potager/{potagerId}/carre/{carreId}/plantation")
public class CarrePlanteController {

    private final CarrePlanteService carrePlanteService;
    private final PlanteService planteService;
    private final CarreService carreService;

    @Autowired
    public CarrePlanteController(CarrePlanteService carrePlanteService, PlanteService planteService, CarreService carreService) {
        this.carrePlanteService = carrePlanteService;
        this.planteService = planteService;
        this.carreService = carreService;
    }

	@GetMapping("/list")
	public String getAll(@PathVariable Integer potagerId, @PathVariable Integer carreId, Model model) {
		List<CarrePlante> carrePlanteList = carrePlanteService.findAll(carreId);
        model.addAttribute("carrePlanteList", carrePlanteList);
        model.addAttribute("potagerId", potagerId);
        model.addAttribute("carreId", carreId);
        model.addAttribute("title", "Plantation");
		return "carre-plante/carre-plante-list";
	}
	
	@GetMapping("/add")
	public String showCreateCarrePlanteForm(@PathVariable Integer potagerId, @PathVariable Integer carreId, Model model) {
		CarrePlante carrePlante = new CarrePlante();
        model.addAttribute("carrePlante", carrePlante);
        List<Plante> planteList = planteService.findAll();
        model.addAttribute("planteList", planteList);
        model.addAttribute("potagerId", potagerId);
        model.addAttribute("carreId", carreId);
        model.addAttribute("title", "Plantation");
		return "carre-plante/carre-plante-form";
		
	}
	
	@PostMapping("/valider")
    public String create(@PathVariable Integer potagerId, @PathVariable Integer carreId, @Valid CarrePlante carrePlante, BindingResult result) {
        if (result.hasErrors()) {
            return "carre-plante/carre-plante-form";
        } else {
            Carre carre = carreService.findById(carreId);
            carrePlanteService.create(carre, carrePlante);
            return "redirect:/potager/" + potagerId + "/carre/" + carreId + "/plantation/list";
        }
    }

}
