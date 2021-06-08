package com.example.patatapp.bo;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Plante {

	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String name;

    @OneToMany(mappedBy = "plante")
    List<CarrePlante> carrePlanteList = new ArrayList<>();

    @Override
    public String toString() {
        return "Plante{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", carrePlanteList.size=" + carrePlanteList.size() +
                '}';
    }
}
