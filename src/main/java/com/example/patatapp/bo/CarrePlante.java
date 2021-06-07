package com.example.patatapp.bo;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class CarrePlante {

    @EmbeddedId
    private CarrePlantePK id;

    @ManyToOne
    @MapsId("carreId")
    @JoinColumn(name = "carre_id")
    Carre carre;

    @ManyToOne
    @MapsId("planteId")
    @JoinColumn(name = "plante_id")
    Plante plante;

    private int quantite;
}
