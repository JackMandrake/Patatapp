package com.example.patatapp.dao;

import com.example.patatapp.bo.Plante;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanteDaoInterface extends CrudRepository<Plante, Integer> {

//    @Query("select p from Plante p where p.")
//    List<Plante> findAllByCarreId(Integer carreId);

}
