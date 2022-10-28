package com.robot.repository;

import com.robot.model.Survivor;
import com.robot.model.SurvivorReports;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurvivorInfectedRepository extends CrudRepository<SurvivorReports, Long> {

    SurvivorReports findByReporter(Survivor survivor);

    SurvivorReports findByInfected(Survivor survivor);

    Long countByInfected(Survivor survivor);

    void deleteByInfected(Survivor survivor);

    void deleteByReporter(Survivor survivor);

}
