package com.example.patatapp.service;

import java.util.List;

import com.example.patatapp.bo.Potager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.patatapp.dao.CarreDaoInterface;
import com.example.patatapp.bo.Carre;

import javax.transaction.Transactional;

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

	public void create(Potager potager, Carre carre) throws BllException {
		valid(potager, carre);
		potager.addCarre(carre);
		dao.save(carre);
	}

	public void deleteById(Integer id) {
		dao.deleteById(id);
	}

	public Carre findById(Integer id) {
		return dao.findById(id).orElse(null);
	}

	@Transactional
	public void update(Carre carre) throws BllException {
		Potager potager = carre.getPotager();
		valid(potager, carre);
		dao.save(carre);
	}

	private void valid(Potager potager, Carre carre) throws BllException {
		int occupiedSurface = potager.getCarreList().stream().filter(car -> !car.getId().equals(carre.getId()))
				.map(Carre::getSurface).mapToInt(Integer::intValue).sum();
		int availableSurface = potager.getSurface() - occupiedSurface;
		if (carre.getSurface() > availableSurface) {
			throw new BllException("La surface disponible dans le potager est insufissante (" + availableSurface + " m²)");
		}
	}

}
