package com.webapp.craictivity.controller;

import com.webapp.craictivity.entity.Participant;
import com.webapp.craictivity.service.ParticipantService;
import com.webapp.craictivity.service.WorkshopService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ParticipantController {

    private ParticipantService participantService;
    private WorkshopService workshopService;

    //Is it ok to have workshopService in here? or is there a better way to do it?
    public ParticipantController(ParticipantService participantService, WorkshopService workshopService) {
        super();
        this.participantService = participantService;
        this.workshopService = workshopService;
    }

    //display the form to register to a specific workshop
    @GetMapping("/participant/register/{id}")
    public String createParticipantForm(@PathVariable Long id, Model model){
        model.addAttribute("workshop", workshopService.getWorkshopById(id));
        return "create_participant";
    }

//    public String saveParticipant(@ModelAttribute("participant") Participant participant){
//        //save the participant object to the database
//        participantService.saveParticipant(participant);
//
//    }
}
