package com.robot.controller;

import com.robot.dto.Robot;
import com.robot.dto.RobotResponse;
import com.robot.model.Survivor;
import com.robot.service.SurvivorService;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("report")
public class ReportController {

    @Autowired
    private SurvivorService survivorService;

    RestTemplate restTemplate = new RestTemplate();

    String robotsUrl = "https://robotstakeover20210903110417.azurewebsites.net/robotcpu";

    @ApiOperation(value = "Get infected survivors")
    @GetMapping("/infected/survivors")
    public ResponseEntity<RobotResponse> getInfectedSurvivors() {
        RobotResponse robot_response = new RobotResponse();
        try {
            List<Survivor> survivors = survivorService.getInfected("INFECTED");
            robot_response.setTitle("success");
            robot_response.setMessage("infected survivors found successfully");
            robot_response.setData(survivors);
            return new ResponseEntity(robot_response, HttpStatus.OK);
        } catch (Exception ex) {
            robot_response.setTitle("error");
            robot_response.setMessage("system busy, please try again later!");
            robot_response.setDetail(ex.toString());
            return new ResponseEntity(robot_response, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @ApiOperation(value = "Get healthy survivors")
    @GetMapping("/healthy/survivors")
    public ResponseEntity<RobotResponse> getHealthySurvivors() {
        RobotResponse robot_response = new RobotResponse();
        try {
            List<Survivor> survivors = survivorService.getInfected("HEALTHY");
            robot_response.setTitle("success");
            robot_response.setMessage("healthy survivors found successfully");
            robot_response.setData(survivors);
            return new ResponseEntity(robot_response, HttpStatus.OK);
        } catch (Exception ex) {
            robot_response.setTitle("error");
            robot_response.setMessage("system busy, please try again later!");
            robot_response.setDetail(ex.toString());
            return new ResponseEntity(robot_response, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @ApiOperation(value = "Get all robots")
    @GetMapping("/get/all/robots")
    public ResponseEntity<RobotResponse> getAllBots() {
        RobotResponse robot_response = new RobotResponse();
        try {
            ArrayList<Robot> robots = (ArrayList) restTemplate.getForObject(robotsUrl, Object.class);
            robot_response.setTitle("success");
            robot_response.setMessage("bots found successfully");
            robot_response.setData(robots);
            return new ResponseEntity(robot_response, HttpStatus.OK);

        } catch (Exception ex) {
            robot_response.setTitle("error");
            robot_response.setMessage("system busy, please try again later!");
            robot_response.setDetail(ex.toString());
            return new ResponseEntity(robot_response, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @ApiOperation(value = "Get land robots")
    @GetMapping("/get/land/robots")
    public ResponseEntity<RobotResponse> getLandRobots() {
        RobotResponse robot_response = new RobotResponse();
        try {
            ResponseEntity<Robot[]> response =
                    restTemplate.getForEntity(
                            robotsUrl,
                            Robot[].class);
            Robot[] robots = response.getBody();
            List<Robot> land_robots = Arrays.stream(robots).filter(bot -> bot.getCategory().contains("Land")).collect(Collectors.toList());
            robot_response.setTitle("success");
            robot_response.setMessage("land bots found successfully");
            robot_response.setData(land_robots);
            return new ResponseEntity(robot_response, HttpStatus.OK);

        } catch (Exception ex) {
            robot_response.setTitle("error");
            robot_response.setMessage("system busy, please try again later!");
            robot_response.setDetail(ex.toString());
            return new ResponseEntity(robot_response, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @ApiOperation(value = "Get flying robots")
    @GetMapping("/get/flying/robots")
    public ResponseEntity<RobotResponse> getFlyingRobots() {
        RobotResponse robot_response = new RobotResponse();
        try {
            ResponseEntity<Robot[]> response =
                    restTemplate.getForEntity(
                            robotsUrl,
                            Robot[].class);
            Robot[] robots = response.getBody();
            List<Robot> flying_robots = Arrays.stream(robots).filter(bot -> bot.getCategory().contains("Flying")).collect(Collectors.toList());
            robot_response.setTitle("success");
            robot_response.setMessage("flying bots found successfully");
            robot_response.setData(flying_robots);
            return new ResponseEntity(robot_response, HttpStatus.OK);

        } catch (Exception ex) {
            robot_response.setTitle("error");
            robot_response.setMessage("system busy, please try again later!");
            robot_response.setDetail(ex.toString());
            return new ResponseEntity(robot_response, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @ApiOperation(value = "Get percentage of infected and healthy survivors")
    @GetMapping("/get/infection/percentage")
    public ResponseEntity<RobotResponse> getSurvivorInfectionPercentage() {
        RobotResponse robot_response = new RobotResponse();
        try {
            long all=survivorService.countAll();
            long infected=((survivorService.countInfected("INFECTED"))*100)/all;
            long healthy=((survivorService.countInfected("HEALTHY"))*100)/all;
           Percentage percentage=new Percentage();
           percentage.setInfected(infected);
           percentage.setHealthy(healthy);
            robot_response.setTitle("success");
            robot_response.setMessage("Percentage report produced");
            robot_response.setData(percentage);
            return new ResponseEntity(robot_response, HttpStatus.OK);

        } catch (Exception ex) {
            ex.printStackTrace();
            robot_response.setTitle("error");
            robot_response.setMessage("system busy, please try again later!");
            robot_response.setDetail(ex.toString());
            return new ResponseEntity(robot_response, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @Data
    class Percentage{
        private Long infected;
        private Long healthy;
    }


}
