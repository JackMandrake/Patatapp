package com.example.patatapp.bo;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Potager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String name;

    @Min(1)
    private int surface;

    @NotBlank
    private String zipCode;

    @NotBlank
    private String city;

    @OneToMany(cascade = CascadeType.MERGE)
    @JoinColumn(name = "potager_id")
    private List<Carre> carreList = new ArrayList<>();

    public void addCarre(Carre carre) {
        carreList.add(carre);
        carre.setPotager(this);
    }
}
