package com.example.patatapp.bo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Action {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String event;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @NotEmpty
    @ManyToMany
    @JoinTable(name = "action_carre", joinColumns = { @JoinColumn(name = "action_id") }, inverseJoinColumns = { @JoinColumn(name = "carre_id") })
    private List<Carre> carres = new ArrayList<>();

}
