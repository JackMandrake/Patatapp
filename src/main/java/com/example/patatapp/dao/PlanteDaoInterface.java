package com.example.patatapp.dao;

import com.example.patatapp.bo.Plante;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanteDaoInterface extends CrudRepository<Plante, Integer> {
}
