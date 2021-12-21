package com.webapp.craictivity.controller;

import com.webapp.craictivity.service.WorkshopService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ParticipantController {

    private final WorkshopService workshopService;

    public ParticipantController(final WorkshopService workshopService) {
        this.workshopService = workshopService;
    }

    //display list of participants for a specific workshop
    @GetMapping("/participants/{workshopId}")
    public String displayParticipants(@PathVariable Long workshopId, Model model){
        model.addAttribute("participants", workshopService.getWorkshopById(workshopId).getEnrollments());
        return "participants_list";
    }
}
