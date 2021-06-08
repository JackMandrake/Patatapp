package com.example.patatapp.service;

import com.example.patatapp.bo.Carre;
import com.example.patatapp.bo.CarrePlante;
import com.example.patatapp.bo.CarrePlantePK;
import com.example.patatapp.dao.CarrePlanteDaoInterface;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarrePlanteService {

	private final CarrePlanteDaoInterface dao;

	public CarrePlanteService(CarrePlanteDaoInterface dao) {
		this.dao = dao;
	}

	public void create(Carre carre, CarrePlante carrePlante) {
		carrePlante.setCarre(carre);
		carre.getCarrePlanteList().add(carrePlante);
		carrePlante.getPlante().getCarrePlanteList().add(carrePlante);
		carrePlante.setId(new CarrePlantePK(carre.getId(), carrePlante.getPlante().getId()));
		System.out.println("*******************************************");
		System.out.println(carrePlante);
		System.out.println("*******************************************");
		dao.save(carrePlante);
	}

	public List<CarrePlante> findAll() {
		return (List<CarrePlante>) dao.findAll();
	}
}
