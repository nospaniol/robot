package com.robot.dto;

import lombok.Data;

@Data
public class SurvivorRequestLoc {

    private String survivorId;
    private Double lastLatitude;
    private Double lastLongitude;

}

