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
		dao.save(carrePlante);
	}

	public List<CarrePlante> findAll(Integer carreId) {
		return dao.findAllByCarreId(carreId);
	}
}
