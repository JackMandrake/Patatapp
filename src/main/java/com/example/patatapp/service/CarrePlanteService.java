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

	public void create(Carre carre, CarrePlante carrePlante) throws BllException {


		// en cm²
		int occupiedSurface = carre.getCarrePlanteList().stream()
				.map(cp -> cp.getPlante().getSurface() * cp.getQuantite()).mapToInt(Integer::intValue).sum();
		//en cm²
		int availableSurface = carre.getSurface() * 10000 - occupiedSurface;
		if ((carrePlante.getPlante().getSurface() * carrePlante.getQuantite()) > availableSurface) {
			throw new BllException("La surface disponible dans le carré est insufissante (" + availableSurface + " cm²)");
		}


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
