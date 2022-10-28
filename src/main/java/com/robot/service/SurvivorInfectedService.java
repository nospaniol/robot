package com.robot.service;

import com.robot.model.Survivor;
import com.robot.model.SurvivorReports;
import org.springframework.stereotype.Service;

@Service
public interface SurvivorInfectedService extends GenericService<SurvivorReports> {

    SurvivorReports findByReporter(Survivor survivor);

    SurvivorReports findByReporterAndInfetced(Survivor reporter,Survivor infected);

    SurvivorReports findByInfetced(Survivor survivor);

    Long countByInfetced(Survivor survivor);

}
