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
public class Carre {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Integer surface;
	
	@NotBlank
	private String typeDeSol;
	
	@NotBlank
	private String exposition;

	@ManyToOne(cascade = CascadeType.MERGE)
	private Potager potager;

	@OneToMany(mappedBy = "carre")
	List<CarrePlante> carrePlanteList = new ArrayList<>();

	@Override
	public String toString() {
		return "Carre{" +
				"id=" + id +
				", surface=" + surface +
				", typeDeSol='" + typeDeSol + '\'' +
				", exposition='" + exposition + '\'' +
				", carrePlanteList.size=" + carrePlanteList.size() +
				'}';
	}
}
