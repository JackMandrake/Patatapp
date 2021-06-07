package com.example.patatapp.bo;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "potager_id")
    private List<Carre> carreList = new ArrayList<>();

    public void addCarre(Carre carre) {
        carreList.add(carre);
        carre.setPotager(this);
    }
}
