package com.example.patatapp.bo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
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

    private String event;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    private boolean isPotagerChosen;

    @ManyToMany
    @JoinTable(name = "action_potager", joinColumns = { @JoinColumn(name = "action_id") }, inverseJoinColumns = { @JoinColumn(name = "potager_id") })
    private List<Potager> potagers = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "action_carre", joinColumns = { @JoinColumn(name = "action_id") }, inverseJoinColumns = { @JoinColumn(name = "carre_id") })
    private List<Carre> carres = new ArrayList<>();

}
