package com.robot.service.impl;

import com.robot.model.Survivor;
import com.robot.repository.SurvivorRepository;
import com.robot.service.SurvivorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class SurvivorServiceImpl implements SurvivorService {

    @Autowired
    private SurvivorRepository survivorRepository;

    @Override
    public Survivor save(Survivor entity) {
       return survivorRepository.save(entity);
    }

    @Override
    public Survivor update(Survivor entity) {
        return survivorRepository.save(entity);
    }

    @Override
    public void delete(Survivor entity) {
        survivorRepository.delete(entity);
    }

    @Override
    public void deleteByUniqueId(String id) {
        survivorRepository.deleteBySurvivorId(id);
    }

    @Override
    public List<Survivor> getInfected(String status) {
        return survivorRepository.findByInfectedStatus(status);
    }

    @Override
    public Long countInfected(String status) {
        return survivorRepository.countByInfectedStatus(status);
    }

    @Override
    public void delete(Long id) {
        survivorRepository.deleteById(id);
    }

    @Override
    public void deleteAll(List<Survivor> entities) {
        survivorRepository.deleteAll(entities);
    }

    @Override
    public Long countAll() {
        return survivorRepository.count();
    }


    @Override
    public Survivor find(Long survivorId) {
        Optional<Survivor> survivor=survivorRepository.findById(survivorId);
        if(!survivor.isPresent()){
            return null;
        }
        return survivor.get();
    }

    @Override
    public Survivor findByUniqueId(String id) {
        return survivorRepository.findBySurvivorId(id);
    }

    @Override
    public List<Survivor> findAll() {
        return (List)survivorRepository.findAll();
    }
}
