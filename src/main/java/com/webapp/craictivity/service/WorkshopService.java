package com.webapp.craictivity.service;

import com.webapp.craictivity.entity.Participant;
import com.webapp.craictivity.entity.Workshop;

import java.util.List;
import java.util.Set;

public interface WorkshopService {

    List<Workshop> getAllWorkshops();
    Workshop saveWorkshop(Workshop workshop);
    Workshop getWorkshopById(Long id);
    Workshop updateWorkshop(Workshop workshop);
    void deleteWorkshopById(Long id);

    //Get workshops by keyword
    List<Workshop> findByKeyword(String keyword);
}
