package com.example.patatapp.dao;

import com.example.patatapp.bo.Carre;
import com.example.patatapp.bo.CarrePlante;
import com.example.patatapp.bo.CarrePlantePK;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarrePlanteDaoInterface extends CrudRepository<CarrePlante, CarrePlantePK>{

    List<CarrePlante> findAllByCarreId(Integer carreId);

    @Query("select cp.carre from CarrePlante cp where cp.plante.id=:planteId")
    List<Carre> findAllByPlanteId(@Param("planteId") Integer planteId);
}
