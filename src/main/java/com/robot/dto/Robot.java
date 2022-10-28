package com.robot.dto;

import lombok.Data;

import java.io.Serializable;


@Data
public class Robot implements Serializable {
    private String model;
    private String serialNumber;
    private String manufacturedDate;
    private String category;
}

