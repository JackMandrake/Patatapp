package com.example.patatapp.dao;

import com.example.patatapp.bo.Carre;
import com.example.patatapp.bo.CarrePlante;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CarrePlanteDaoInterface extends CrudRepository<CarrePlante, Integer>{

}
