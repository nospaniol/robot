package com.robot.dto;

import lombok.Data;


@Data
public class SurvivorRequest {

    private String name;
    private int age;
    private String gender;
    private Double lastLatitude;
    private Double lastLongitude;
    SurvivorResource resources;

}

