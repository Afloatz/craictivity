package com.webapp.craictivity.service.impl;

import com.webapp.craictivity.entity.Workshop;
import com.webapp.craictivity.repository.WorkshopRepository;
import com.webapp.craictivity.service.WorkshopService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkshopServiceImpl implements WorkshopService {

    private WorkshopRepository workshopRepository;

    public WorkshopServiceImpl(WorkshopRepository workshopRepository) {
        super();
        this.workshopRepository = workshopRepository;
    }

    @Override
    public List<Workshop> getAllWorkshops() {
        return workshopRepository.findAll(); //returns a list of workshops
    }

    @Override
    public Workshop saveWorkshop(Workshop workshop) {
        return workshopRepository.save(workshop);
    }

    @Override
    public Workshop getWorkshopById(Long id) {
        return workshopRepository.findById(id).get();
    }

    @Override
    public Workshop updateWorkshop(Workshop workshop) {
        return workshopRepository.save(workshop);
    }

    @Override
    public void deleteWorkshopById(Long id) {
        workshopRepository.deleteById(id);
    }

    @Override
    public List<Workshop> findByKeyword(String keyword) {
        return workshopRepository.findByKeyword(keyword);
    }
}
