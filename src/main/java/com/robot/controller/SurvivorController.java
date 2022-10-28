package com.robot.controller;

import com.robot.dto.*;
import com.robot.model.Survivor;
import com.robot.model.SurvivorReports;
import com.robot.model.SurvivorResources;
import com.robot.service.SurvivorInfectedService;
import com.robot.service.SurvivorResourceService;
import com.robot.service.SurvivorService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("survivor")
public class SurvivorController {


    RestTemplate restTemplate = new RestTemplate();
    @Autowired
    private SurvivorService survivorService;

    @Autowired
    private SurvivorInfectedService survivorInfectedService;

    @Autowired
    private SurvivorResourceService survivorResourceService;

    @ApiOperation(value = "Add a new survivor, with resources")
    @PostMapping("/create")
    public ResponseEntity<RobotResponse> addNewSurvivor(@RequestBody SurvivorRequest request) {
        RobotResponse robot_response = new RobotResponse();
        try {

            if (request.getName() == null || request.getName().isEmpty()) {
                robot_response.setTitle("error");
                robot_response.setMessage("please, names not found!");
                return new ResponseEntity(robot_response, HttpStatus.OK);
            }

            if (request.getGender() == null || request.getGender().isEmpty()) {
                robot_response.setTitle("error");
                robot_response.setMessage("please, gender not found!");
                return new ResponseEntity(robot_response, HttpStatus.OK);
            }

            if (request.getLastLatitude() == null) {
                robot_response.setTitle("error");
                robot_response.setMessage("please, pass in the current location!");
                return new ResponseEntity(robot_response, HttpStatus.OK);
            }

            if (request.getLastLongitude() == null) {
                robot_response.setTitle("error");
                robot_response.setMessage("please, pass in the current location!");
                return new ResponseEntity(robot_response, HttpStatus.OK);
            }

            if (request.getAge() == 0) {
                robot_response.setTitle("error");
                robot_response.setMessage("please fill in the age!");
                return new ResponseEntity(robot_response, HttpStatus.OK);
            }
            if (request.getResources() == null) {
                robot_response.setTitle("error");
                robot_response.setMessage("please pass in the resources!");
                return new ResponseEntity(robot_response, HttpStatus.OK);
            }


            if (request.getResources().getAmmunition() == null || request.getResources().getAmmunition().isEmpty()) {
                robot_response.setTitle("error");
                robot_response.setMessage("please, ammunation not found!");
                return new ResponseEntity(robot_response, HttpStatus.OK);
            }

            if (request.getResources().getFood() == null || request.getResources().getFood().isEmpty()) {
                robot_response.setTitle("error");
                robot_response.setMessage("please, ammunation not found!");
                return new ResponseEntity(robot_response, HttpStatus.OK);
            }

            if (request.getResources().getMedication() == null || request.getResources().getMedication().isEmpty()) {
                robot_response.setTitle("error");
                robot_response.setMessage("please, ammunation not found!");
                return new ResponseEntity(robot_response, HttpStatus.OK);
            }

            if (request.getResources().getWater() == null || request.getResources().getWater().isEmpty()) {
                robot_response.setTitle("error");
                robot_response.setMessage("please, ammunation not found!");
                return new ResponseEntity(robot_response, HttpStatus.OK);
            }


            Survivor requestSurvivor = new Survivor();
            requestSurvivor.setSurvivorId(UUID.randomUUID().toString());
            requestSurvivor.setAge(request.getAge());
            requestSurvivor.setGender(request.getGender());
            requestSurvivor.setName(request.getName());
            requestSurvivor.setLastLatitude(request.getLastLatitude());
            requestSurvivor.setLastLongitude(request.getLastLongitude());
            requestSurvivor.setInfectedStatus("HEALTHY");

            Survivor newSurvivor = survivorService.save(requestSurvivor);
            if (newSurvivor == null) {
                robot_response.setTitle("error");
                robot_response.setMessage("Failed to save survivor");
                return new ResponseEntity(robot_response, HttpStatus.EXPECTATION_FAILED);
            } else {
                SurvivorResources survivorResources = new SurvivorResources();
                survivorResources.setSurvivor(newSurvivor);
                survivorResources.setAmmunition(request.getResources().getAmmunition());
                survivorResources.setFood(request.getResources().getFood());
                survivorResources.setMedication(request.getResources().getMedication());
                survivorResources.setWater(request.getResources().getWater());
                SurvivorResources survivorRes = survivorResourceService.save(survivorResources);
                if (survivorRes == null) {
                    survivorService.delete(newSurvivor);
                    robot_response.setTitle("error");
                    robot_response.setMessage("Failed to save resource");
                    return new ResponseEntity(robot_response, HttpStatus.EXPECTATION_FAILED);
                }
                robot_response.setTitle("success");
                robot_response.setMessage("survivor updated successfully");
                robot_response.setData(newSurvivor);
                return new ResponseEntity(robot_response, HttpStatus.CREATED);
            }

        } catch (Exception ex) {
            robot_response.setTitle("error");
            robot_response.setMessage("system busy, please try again later!");
            robot_response.setDetail(ex.toString());
            return new ResponseEntity(robot_response, HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Update survivor location")
    @PostMapping("/update/location")
    public ResponseEntity<RobotResponse> updateSurvivorLocation(@RequestBody SurvivorRequestLoc request) {
        RobotResponse robot_response = new RobotResponse();
        try {

            if (request.getSurvivorId() == null || request.getSurvivorId().isEmpty()) {
                robot_response.setTitle("error");
                robot_response.setMessage("please, pass in the current location!");
                return new ResponseEntity(robot_response, HttpStatus.OK);
            }

            if (request.getLastLatitude() == null) {
                robot_response.setTitle("error");
                robot_response.setMessage("please, pass in the current location!");
                return new ResponseEntity(robot_response, HttpStatus.OK);
            }

            if (request.getLastLongitude() == null) {
                robot_response.setTitle("error");
                robot_response.setMessage("please, pass in the current location!");
                return new ResponseEntity(robot_response, HttpStatus.OK);
            }


            Survivor survivor = survivorService.findByUniqueId(request.getSurvivorId());
            if (survivor == null) {
                robot_response.setTitle("error");
                robot_response.setMessage("please, survivor id not found!");
                return new ResponseEntity(robot_response, HttpStatus.OK);
            }
            survivor.setLastLatitude(request.getLastLatitude());
            survivor.setLastLongitude(request.getLastLongitude());

            Survivor updatedSurvivor = survivorService.save(survivor);
            if (updatedSurvivor == null) {
                robot_response.setTitle("error");
                robot_response.setMessage("Failed to save survivor");
                return new ResponseEntity(robot_response, HttpStatus.OK);
            } else {
                robot_response.setTitle("success");
                robot_response.setMessage("survivor updated successfully");
                robot_response.setData(updatedSurvivor);
                return new ResponseEntity(robot_response, HttpStatus.CREATED);
            }

        } catch (Exception ex) {
            robot_response.setTitle("error");
            robot_response.setMessage("system busy, please try again later!");
            robot_response.setDetail(ex.toString());
            return new ResponseEntity(robot_response, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @ApiOperation(value = "Flag infected survivor ")
    @PostMapping("/flag")
    public ResponseEntity<RobotResponse> flagSurvivor(@RequestBody SurvivorReportInfected request) {
        RobotResponse robot_response = new RobotResponse();
        try {

            if (request.getInfectedSurvivorId() == null || request.getInfectedSurvivorId().isEmpty()) {
                robot_response.setTitle("error");
                robot_response.setMessage("please, pass the infected survivor id!");
                return new ResponseEntity(robot_response, HttpStatus.OK);
            }

            if (request.getReportingSurvivorId() == null || request.getReportingSurvivorId().isEmpty()) {
                robot_response.setTitle("error");
                robot_response.setMessage("please, pass the reporting survivor id!");
                return new ResponseEntity(robot_response, HttpStatus.OK);
            }

            if (request.getReportingSurvivorId() == request.getInfectedSurvivorId()) {
                robot_response.setTitle("error");
                robot_response.setMessage("please, you can't report self!");
                return new ResponseEntity(robot_response, HttpStatus.OK);
            }

            Survivor infectedSurvivor = survivorService.findByUniqueId(request.getInfectedSurvivorId());
            if (infectedSurvivor == null) {
                robot_response.setTitle("error");
                robot_response.setMessage("please, infected survivor id not found!");
                return new ResponseEntity(robot_response, HttpStatus.OK);
            }

            Survivor reportingSurvivor = survivorService.findByUniqueId(request.getReportingSurvivorId());
            if (reportingSurvivor == null) {
                robot_response.setTitle("error");
                robot_response.setMessage("please, reporting survivor id not found!");
                return new ResponseEntity(robot_response, HttpStatus.OK);
            }




            SurvivorReports flagReport = new SurvivorReports();
            flagReport.setReporter(reportingSurvivor);
            flagReport.setInfected(infectedSurvivor);
            SurvivorReports flaggedSurvivor = survivorInfectedService.save(flagReport);

            if (flaggedSurvivor == null) {
                robot_response.setTitle("error");
                robot_response.setMessage("Failed to save report");
                return new ResponseEntity(robot_response, HttpStatus.OK);
            } else {
                long infectCount = survivorInfectedService.countByInfetced(infectedSurvivor);
                if (infectCount >= 3) {
                    infectedSurvivor.setInfectedStatus("INFECTED");
                    survivorService.save(infectedSurvivor);
                }
                robot_response.setTitle("success");
                robot_response.setMessage("survivor reported successfully");
                robot_response.setData(flaggedSurvivor);
                return new ResponseEntity(robot_response, HttpStatus.CREATED);
            }

        } catch (Exception ex) {
            robot_response.setTitle("error");
            robot_response.setMessage("system busy, please try again later!");
            robot_response.setDetail(ex.toString());
            return new ResponseEntity(robot_response, HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Get all survivors ")
    @GetMapping("/get/all")
    public ResponseEntity<RobotResponse> getAllSurvivors() {
        RobotResponse robot_response = new RobotResponse();
        try {
            List<Survivor> all_survivors = survivorService.findAll();
            robot_response.setTitle("success");
            robot_response.setMessage("survivor reported successfully");
            robot_response.setData(all_survivors);
            return new ResponseEntity(robot_response, HttpStatus.OK);

        } catch (Exception ex) {
            robot_response.setTitle("error");
            robot_response.setMessage("system busy, please try again later!");
            robot_response.setDetail(ex.toString());
            return new ResponseEntity(robot_response, HttpStatus.OK);
        }
    }


}
