package com.robot.service.impl;

import com.robot.model.Survivor;
import com.robot.model.SurvivorResources;
import com.robot.repository.SurvivorResourcesRepository;
import com.robot.service.SurvivorResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class SurvivorResourcesServiceImpl implements SurvivorResourceService {

    @Autowired
    private SurvivorResourcesRepository survivorResourcesRepository;

    @Override
    public SurvivorResources save(SurvivorResources entity) {
        return survivorResourcesRepository.save(entity);
    }

    @Override
    public SurvivorResources update(SurvivorResources entity) {
        return survivorResourcesRepository.save(entity);
    }

    @Override
    public void delete(SurvivorResources entity) {
        survivorResourcesRepository.delete(entity);
    }

    @Override
    public void delete(Long id) {
        survivorResourcesRepository.deleteById(id);
    }

    @Override
    public void deleteAll(List<SurvivorResources> entities) {
        survivorResourcesRepository.deleteAll(entities);
    }

    @Override
    public Long countAll() {
        return survivorResourcesRepository.count();
    }


    @Override
    public SurvivorResources find(Long survivorId) {
        Optional<SurvivorResources> survivor = survivorResourcesRepository.findById(survivorId);
        if (!survivor.isPresent()) {
            return null;
        }
        return survivor.get();
    }

    @Override
    public List<SurvivorResources> findAll() {
        return (List) survivorResourcesRepository.findAll();
    }

    @Override
    public SurvivorResources findBySurvivor(Survivor survivor) {
        return survivorResourcesRepository.findBySurvivor(survivor);
    }

    @Override
    public void deleteBySurvivor(Survivor survivor) {
        survivorResourcesRepository.deleteBySurvivor(survivor);
    }
}
