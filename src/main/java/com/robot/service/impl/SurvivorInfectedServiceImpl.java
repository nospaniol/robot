package com.robot.service.impl;

import com.robot.model.Survivor;
import com.robot.model.SurvivorReports;
import com.robot.repository.SurvivorInfectedRepository;
import com.robot.service.SurvivorInfectedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class SurvivorInfectedServiceImpl implements SurvivorInfectedService {

    @Autowired
    private SurvivorInfectedRepository survivorInfectedRepository;

    @Override
    public SurvivorReports save(SurvivorReports entity) {
       return survivorInfectedRepository.save(entity);
    }

    @Override
    public SurvivorReports update(SurvivorReports entity) {
        return survivorInfectedRepository.save(entity);
    }

    @Override
    public void delete(SurvivorReports entity) {
        survivorInfectedRepository.delete(entity);
    }


    @Override
    public void delete(Long id) {
        survivorInfectedRepository.deleteById(id);
    }

    @Override
    public void deleteAll(List<SurvivorReports> entities) {
        survivorInfectedRepository.deleteAll(entities);
    }

    @Override
    public Long countAll() {
        return survivorInfectedRepository.count();
    }


    @Override
    public SurvivorReports find(Long survivorId) {
        Optional<SurvivorReports> survivor=survivorInfectedRepository.findById(survivorId);
        if(!survivor.isPresent()){
            return null;
        }
        return survivor.get();
    }


    @Override
    public List<SurvivorReports> findAll() {
        return (List)survivorInfectedRepository.findAll();
    }

    @Override
    public SurvivorReports findByReporter(Survivor survivor) {
        return survivorInfectedRepository.findByReporter(survivor);
    }

    @Override
    public List<SurvivorReports> findByReporterAndInfected(Survivor reporter, Survivor infected) {
        return survivorInfectedRepository.findByReporterAndInfected(reporter,infected);
    }

    @Override
    public SurvivorReports findByInfetced(Survivor survivor) {
        return survivorInfectedRepository.findByReporter(survivor);
    }

    @Override
    public Long countByInfetced(Survivor survivor) {
        return survivorInfectedRepository.countByInfected(survivor);
    }
}
