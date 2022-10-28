package com.robot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class SurvivorResources {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="survivorId")
    private Survivor survivor;

    private String water;
    private String food;
    private String medication;
    private String ammunition;
}
