package com.robot.repository;

import com.robot.model.Survivor;
import com.robot.model.SurvivorResources;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SurvivorResourcesRepository extends CrudRepository<SurvivorResources, Long> {

    SurvivorResources findBySurvivor(Survivor survivor);

    void deleteBySurvivor(Survivor survivor);

}
