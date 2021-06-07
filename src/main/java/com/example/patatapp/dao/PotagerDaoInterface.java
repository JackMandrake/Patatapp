package com.example.patatapp.dao;

import com.example.patatapp.bo.Potager;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PotagerDaoInterface extends CrudRepository<Potager, Integer> {
}
