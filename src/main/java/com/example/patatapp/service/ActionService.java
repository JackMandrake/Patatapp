package com.example.patatapp.service;

import com.example.patatapp.bo.Action;
import com.example.patatapp.bo.Carre;
import com.example.patatapp.dao.ActionDaoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActionService {

	private final ActionDaoInterface dao;

	@Autowired
	public ActionService(ActionDaoInterface dao) {
		this.dao = dao;
	}

	public void save(Action action) {
		List<Carre> carres = action.getCarres();
		for (Carre carre : carres) {
			carre.getActions().add(action);
		}
		dao.save(action);
	}

	public List<Action> findAll() {
		return (List<Action>) dao.findAll();
	}

	public void deleteById(Integer id) {
		dao.deleteById(id);
	}

	public Action findById(Integer id) {
		return dao.findById(id).orElse(null);
	}

	public void update(Action action) {
		dao.save(action);
	}
}
