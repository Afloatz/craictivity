package com.webapp.craictivity.controller;

import com.webapp.craictivity.service.WorkshopService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//Does it make sense to have that class since I am using only WorkshopService not ParticipantService
@Controller
public class ParticipantController {

    private WorkshopService workshopService;

    public ParticipantController(WorkshopService workshopService) {
        super();
        this.workshopService = workshopService;
    }

    //display list of participants for a specific workshop
    @GetMapping("/participants/list/{id}")
    public String displayParticipants(@PathVariable Long id, Model model){
        model.addAttribute("participants", workshopService.getWorkshopById(id).getParticipants());
        return "participants_list";
    }
}
