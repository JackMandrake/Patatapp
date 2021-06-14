package com.example.patatapp.ui.controller;

import com.example.patatapp.bo.*;
import com.example.patatapp.service.BllException;
import com.example.patatapp.service.CarrePlanteService;
import com.example.patatapp.service.CarreService;
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
import java.time.LocalDate;
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
        carrePlante.setQuantite(1);
        carrePlante.setPlantingDate(LocalDate.now());
        model.addAttribute("carrePlante", carrePlante);
        fillUpTheModel(potagerId, carreId, model);
		return "carre-plante/carre-plante-form";
	}
	
	@PostMapping("/valider")
    public String create(@PathVariable Integer potagerId, @PathVariable Integer carreId, @Valid CarrePlante carrePlante, BindingResult result, Model model) {
        if (result.hasErrors()) {
            fillUpTheModel(potagerId, carreId, model);
            return "carre-plante/carre-plante-form";
        } else {
            Carre carre = carreService.findById(carreId);
            try {
                carrePlanteService.create(carre, carrePlante);
            } catch (BllException e) {
                result.addError(new FieldError("carrePlante", "quantite", e.getMessage()));
                fillUpTheModel(potagerId, carreId, model);
                return "carre-plante/carre-plante-form";
            }
            return "redirect:/potager/" + potagerId + "/carre/" + carreId + "/plantation/list";
        }
    }

    @GetMapping("/{planteId}/delete")
    public String delete(@PathVariable Integer potagerId, @PathVariable Integer carreId, @PathVariable Integer planteId) {
        CarrePlantePK carrePlantePK = new CarrePlantePK(carreId, planteId);
        carrePlanteService.deleteById(carrePlantePK);
        return "redirect:/potager/" + potagerId + "/carre/" + carreId + "/plantation/list";
    }

    @GetMapping("/{planteId}/update")
    public String showUpdateCarrePlanteForm(@PathVariable Integer potagerId, @PathVariable Integer carreId, @PathVariable Integer planteId, Model model) {
        CarrePlantePK carrePlantePK = new CarrePlantePK(carreId, planteId);
        CarrePlante carrePlante = carrePlanteService.findById(carrePlantePK);
        model.addAttribute("carrePlante", carrePlante);
        fillUpTheModel(potagerId, carreId, model);
        return "carre-plante/update-carre-plante-form";
    }

    @PostMapping("/{planteId}/valider-update")
    public String update(@PathVariable Integer potagerId, @PathVariable Integer carreId, @PathVariable Integer planteId, @Valid CarrePlante carrePlante, BindingResult result, Model model) {
        if (result.hasErrors()) {
            fillUpTheModel(potagerId, carreId, model);
            return "carre-plante/update-carre-plante-form";
        } else {
            CarrePlantePK carrePlantePK = new CarrePlantePK(carreId, planteId);
            carrePlante.setId(carrePlantePK);
            carrePlanteService.update(carrePlante);
            return "redirect:/potager/" + potagerId + "/carre/" + carreId + "/plantation/list";
        }
    }

    private void fillUpTheModel(Integer potagerId, Integer carreId, Model model) {
        List<Plante> planteList = planteService.findAll();
        model.addAttribute("planteList", planteList);
        model.addAttribute("potagerId", potagerId);
        model.addAttribute("carreId", carreId);
        model.addAttribute("title", "Plantation");
    }

}
