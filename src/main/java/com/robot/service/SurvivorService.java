package com.robot.service;

import com.robot.model.Survivor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SurvivorService extends GenericService<Survivor> {
    Survivor findByUniqueId(String id);

    void deleteByUniqueId(String id);

    List<Survivor> getInfected(String status);

    Long countInfected(String status);

}
