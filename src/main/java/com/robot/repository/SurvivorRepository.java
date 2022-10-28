package com.robot.repository;

import com.robot.model.Survivor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SurvivorRepository extends CrudRepository<Survivor, Long> {

    Survivor findBySurvivorId(String survivorId);

    List<Survivor> findByInfectedStatus(String survivorId);

    Long countByInfectedStatus(String survivorId);

    void deleteBySurvivorId(String survivorId);

}
