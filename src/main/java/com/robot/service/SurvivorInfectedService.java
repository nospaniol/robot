package com.robot.service;

import com.robot.model.Survivor;
import com.robot.model.SurvivorReports;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SurvivorInfectedService extends GenericService<SurvivorReports> {

    SurvivorReports findByReporter(Survivor survivor);

    List<SurvivorReports> findByReporterAndInfected(Survivor reporter, Survivor infected);

    SurvivorReports findByInfetced(Survivor survivor);

    Long countByInfetced(Survivor survivor);

}
