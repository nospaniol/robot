package com.robot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class SurvivorReports {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="reporter_id")
    private Survivor reporter;

    @ManyToOne
    @JoinColumn(name="infected_id")
    private Survivor infected;

}
