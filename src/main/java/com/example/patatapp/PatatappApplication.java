package com.example.patatapp;

import com.example.patatapp.bo.*;
import com.example.patatapp.dao.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@SpringBootApplication
public class PatatappApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatatappApplication.class, args);
    }

    @Bean
    public RestTemplate getRestTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner init(
            PotagerDaoInterface potagerDao,
            CarreDaoInterface carreDao,
            PlanteDaoInterface planteDao,
            CarrePlanteDaoInterface carrePlanteDao,
            ActionDaoInterface actionDao) {
        return args -> {

            // Creation des plantes
            Plante plante1 = new Plante();
            plante1.setName("Patates");
            plante1.setSurface(10);
            plante1.setImageUrl("/image/patate.png");
            planteDao.save(plante1);
            Plante plante2 = new Plante();
            plante2.setName("Poireaux");
            plante2.setSurface(20);
            plante2.setImageUrl("/image/poireaux.png");
            planteDao.save(plante2);
            Plante plante3 = new Plante();
            plante3.setName("Choux");
            plante3.setSurface(30);
            plante3.setImageUrl("/image/choux.png");
            planteDao.save(plante3);

            // Creation des potager avec carré
            Potager potager1 = new Potager();
            potager1.setName("Plein air");
            potager1.setZipCode("29000");
            potager1.setCity("Quimper");
            potager1.setSurface(100);
            potagerDao.save(potager1);
            Carre carre11 = new Carre();
            carre11.setName("carré sud");
            carre11.setSurface(10);
            carre11.setExposition("sud");
            carre11.setTypeDeSol("terreux");
            potager1.addCarre(carre11);
            carreDao.save(carre11);

            Potager potager2 = new Potager();
            potager2.setName("Sous serre");
            potager2.setZipCode("22110");
            potager2.setCity("Rostrenen");
            potager2.setSurface(50);
            potagerDao.save(potager2);
            Carre carre21 = new Carre();
            carre21.setName("carré nord");
            carre21.setSurface(10);
            carre21.setExposition("nord");
            carre21.setTypeDeSol("sableux");
            potager2.addCarre(carre21);
            carreDao.save(carre21);
            Carre carre22 = new Carre();
            carre22.setName("carré est");
            carre22.setSurface(10);
            carre22.setExposition("est");
            carre22.setTypeDeSol("argileux");
            potager2.addCarre(carre22);
            carreDao.save(carre22);

            // Creation des plantation
            CarrePlante carrePlante1 = new CarrePlante();
            carrePlante1.setPlante(plante1);
            carrePlante1.setQuantite(2);
            carrePlante1.setPlantingDate(LocalDate.now());
            carrePlante1.setHarvestDate(LocalDate.now().plusMonths(3));
            carrePlante1.setCarre(carre21);
            carre21.getCarrePlanteList().add(carrePlante1);
            carrePlante1.getPlante().getCarrePlanteList().add(carrePlante1);
            carrePlante1.setId(new CarrePlantePK(carre21.getId(), carrePlante1.getPlante().getId()));
            carrePlanteDao.save(carrePlante1);

            CarrePlante carrePlante2 = new CarrePlante();
            carrePlante2.setPlante(plante2);
            carrePlante2.setQuantite(4);
            carrePlante2.setPlantingDate(LocalDate.now());
            carrePlante2.setHarvestDate(LocalDate.now().plusMonths(4));
            carrePlante2.setCarre(carre21);
            carre21.getCarrePlanteList().add(carrePlante2);
            carrePlante2.getPlante().getCarrePlanteList().add(carrePlante2);
            carrePlante2.setId(new CarrePlantePK(carre21.getId(), carrePlante2.getPlante().getId()));
            carrePlanteDao.save(carrePlante2);

            CarrePlante carrePlante3 = new CarrePlante();
            carrePlante3.setPlante(plante2);
            carrePlante3.setQuantite(18);
            carrePlante3.setPlantingDate(LocalDate.now());
            carrePlante3.setHarvestDate(LocalDate.now().plusMonths(5));
            carrePlante3.setCarre(carre22);
            carre22.getCarrePlanteList().add(carrePlante3);
            carrePlante3.getPlante().getCarrePlanteList().add(carrePlante3);
            carrePlante3.setId(new CarrePlantePK(carre22.getId(), carrePlante3.getPlante().getId()));
            carrePlanteDao.save(carrePlante3);

            CarrePlante carrePlante4 = new CarrePlante();
            carrePlante4.setPlante(plante3);
            carrePlante4.setQuantite(18);
            carrePlante4.setPlantingDate(LocalDate.now());
            carrePlante4.setHarvestDate(LocalDate.now().plusMonths(2));
            carrePlante4.setCarre(carre22);
            carre22.getCarrePlanteList().add(carrePlante4);
            carrePlante4.getPlante().getCarrePlanteList().add(carrePlante4);
            carrePlante4.setId(new CarrePlantePK(carre22.getId(), carrePlante4.getPlante().getId()));
            carrePlanteDao.save(carrePlante4);

            // Creation des actions
            Action action1 = new Action();
            action1.setEvent("Arroser les plantes");
            action1.setDate(LocalDate.now());
            action1.getCarres().add(carre11);
            action1.getCarres().add(carre22);
            carre11.getActions().add(action1);
            carre22.getActions().add(action1);
            actionDao.save(action1);
        };
    }

}
