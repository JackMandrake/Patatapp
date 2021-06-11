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

    public void create(Plante plante) throws BllException {
        if (findAll().contains(plante)) {
            throw new BllException("Cette plante existe déja!");
        }
        dao.save(plante);
    }

    public void deleteById(Integer id) throws BllException {
        try {
            dao.deleteById(id);
        } catch (Exception e) {
            throw new BllException("Cette plante est en cours de culture");
        }
    }

    public Plante findById(Integer id) {
        return dao.findById(id).orElse(null);
    }

    public void update(Plante plante) {
        dao.save(plante);
    }

}
