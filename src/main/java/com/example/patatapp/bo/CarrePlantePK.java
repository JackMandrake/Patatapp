package com.example.patatapp.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@Embeddable
public class CarrePlantePK implements Serializable {

    @Column(name = "carre_id")
    private Integer carreId;

    @Column(name = "plante_id")
    private Integer planteId;

}
