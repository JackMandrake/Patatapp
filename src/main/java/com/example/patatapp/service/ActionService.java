package com.example.patatapp.service;

import com.example.patatapp.dao.ActionDaoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActionService {

	private final ActionDaoInterface dao;

	@Autowired
	public ActionService(ActionDaoInterface dao) {
		this.dao = dao;
	}

}
