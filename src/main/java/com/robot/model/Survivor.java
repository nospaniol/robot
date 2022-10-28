package com.robot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name="survivor_table")
public class Survivor {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true)
    private Long id;
    private String name;
    private int age;
    private String gender;
    @Column(nullable = false,unique = true)
    private String survivorId;
    private Double lastLatitude;
    private Double lastLongitude;
    private String infectedStatus;

    @JsonIgnore
    @OneToMany(mappedBy = "survivor")
    private List<SurvivorResources> survivorResourcesList;
}
