package com.example.patatapp.service;

import com.example.patatapp.bo.Potager;
import com.example.patatapp.dao.PotagerDaoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PotagerService {

    private final PotagerDaoInterface dao;

    @Autowired
    public PotagerService(PotagerDaoInterface dao) {
        this.dao = dao;
    }

    public List<Potager> findAll() {
        return (List<Potager>) dao.findAll();
    }

    public Potager create(Potager potager) {
        return dao.save(potager);
    }

    public void deleteById(Integer id) {
        dao.deleteById(id);
    }

    public Potager findById(Integer id) throws BllException {
        return dao.findById(id).orElseThrow(() -> new BllException("Le potager avec l'id = " + id + " n'existe pas!"));
    }

    public void update(Potager potager) {
        dao.save(potager);
    }
}
