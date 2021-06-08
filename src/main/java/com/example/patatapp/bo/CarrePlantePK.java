package com.example.patatapp.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@Embeddable
public class CarrePlantePK implements Serializable {

    @Column(name = "carre_id")
    private Integer carreId;

    @Column(name = "plante_id")
    private Integer planteId;

    public CarrePlantePK(Integer carreId, Integer planteId) {
        this.carreId = carreId;
        this.planteId = planteId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarrePlantePK that = (CarrePlantePK) o;
        return carreId.equals(that.carreId) && planteId.equals(that.planteId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carreId, planteId);
    }
}
