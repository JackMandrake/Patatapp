package com.example.patatapp.bo;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
public class Plante {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String name;

    @Min(1)
    private int surface;

    @OneToMany(mappedBy = "plante")
    List<CarrePlante> carrePlanteList = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plante plante = (Plante) o;
        return name.equals(plante.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
