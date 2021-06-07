package com.example.patatapp.bo;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

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

	@ManyToOne(cascade = CascadeType.ALL)
	private Potager potager;

	@OneToMany(mappedBy = "carre")
	List<CarrePlante> carrePlanteList;
	
	
}
