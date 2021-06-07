package com.example.patatapp.service;


import com.example.patatapp.bo.Plante;
import com.example.patatapp.dao.PlanteDaoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanteService {

    private final PlanteDaoInterface dao;

    @Autowired
    public PlanteService(PlanteDaoInterface dao) {
        this.dao = dao;
    }

    public List<Plante> findAll() {
        return (List<Plante>) dao.findAll();
    }

    public void create(Plante plante) {
        dao.save(plante);
    }

    public void deleteById(Integer id) {
        dao.deleteById(id);
    }

    public Plante findById(Integer id) {
        return dao.findById(id).orElse(null);
    }

    public void update(Plante plante) {
        dao.save(plante);
    }
}
