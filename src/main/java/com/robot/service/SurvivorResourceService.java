package com.robot.service;

import com.robot.model.Survivor;
import com.robot.model.SurvivorResources;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SurvivorResourceService extends GenericService<SurvivorResources> {

    SurvivorResources findBySurvivor(Survivor survivor);

    void deleteBySurvivor(Survivor survivor);


}
