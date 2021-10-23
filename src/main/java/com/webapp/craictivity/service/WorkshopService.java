package com.webapp.craictivity.service;

import com.webapp.craictivity.entity.Workshop;

import java.util.List;

public interface WorkshopService {

    List<Workshop> getAllWorkshops();
    Workshop saveWorkshop(Workshop workshop);
    Workshop getWorkshopById(Long id);
    Workshop updateWorkshop(Workshop workshop);
    void deleteWorkshopById(Long id);
}
