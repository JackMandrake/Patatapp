package com.example.patatapp.dao;

import com.example.patatapp.bo.Potager;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.patatapp.bo.Carre;

import java.util.List;

public interface CarreDaoInterface extends CrudRepository<Carre, Integer>{

    List<Carre> findAllByPotagerId(Integer potagerId);

}
