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
}
