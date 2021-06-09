package com.example.patatapp.dao;

import com.example.patatapp.bo.CarrePlante;
import com.example.patatapp.bo.CarrePlantePK;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CarrePlanteDaoInterface extends CrudRepository<CarrePlante, CarrePlantePK>{

    List<CarrePlante> findAllByCarreId(Integer carreId);

}
