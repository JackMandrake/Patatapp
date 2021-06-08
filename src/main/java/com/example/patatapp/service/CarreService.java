package com.example.patatapp.service;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import com.example.patatapp.bo.Potager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.patatapp.dao.CarreDaoInterface;
import com.example.patatapp.bo.Carre;

@Service
public class CarreService {
	
	private final CarreDaoInterface dao;

	
	@Autowired
	public CarreService(CarreDaoInterface dao) {
		this.dao = dao;
	}
	
	public List<Carre> findAll(Integer potagerId) {
		return dao.findAllByPotagerId(potagerId);
	}

	public void create(Potager potager, Carre carre) {
		potager.addCarre(carre);
		dao.save(carre);
	}

	public void deleteById(Integer id) {
		dao.deleteById(id);
		
	}

	public Carre findById(Integer id) {
		return dao.findById(id).orElse(null);
	}

	public void update(@Valid Carre carre) {
		dao.save(carre);		
	}

}
