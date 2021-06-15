package com.example.patatapp.bo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "carre_plante")
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

    @Min(1)
    private int quantite;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate plantingDate;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate harvestDate;

}
