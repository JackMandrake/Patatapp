package com.example.patatapp.ui.controller;

import com.example.patatapp.bo.Potager;
import com.example.patatapp.service.PotagerService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.ArrayList;
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
        return "potager/potager-list";
    }

    @GetMapping("/add")
    public String showCreatePotagerForm(Model model) {
        Potager potager = new Potager();
        potager.setName("nouveau potager");
        model.addAttribute("potager", potager);
        model.addAttribute("title", "Potager");
        return "potager/potager-form";
    }

    @PostMapping("/valider")
    public String create(RestTemplate restTemplate, @Valid Potager potager, BindingResult result, Model model) {
        String json = restTemplate.getForObject("http://api.zippopotam.us/fr/" + potager.getZipCode() , String.class);
        JSONObject jsonObject = new JSONObject(json);
        JSONArray jsonArray = jsonObject.getJSONArray("places");
        List<String> cities = new ArrayList<>();
        jsonArray.iterator().forEachRemaining(o -> cities.add(((JSONObject) o).getString("place name")));
        model.addAttribute("cities", cities);
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
        model.addAttribute("title", "Potager");
        return "potager/potager-overview";
    }

    @GetMapping("/{potagerId}/overview")
    public String potagerOverview(@PathVariable Integer potagerId, Model model) {
        List<Potager> potagerList = service.findAll();
        Potager potager = service.findById(potagerId);
        model.addAttribute("potagerList", potagerList);
        model.addAttribute("potager", potager);
        model.addAttribute("title", "Potager");
        return "potager/potager-overview";
    }



}
