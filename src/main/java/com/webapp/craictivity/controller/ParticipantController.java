package com.webapp.craictivity.controller;

import com.webapp.craictivity.entity.Participant;
import com.webapp.craictivity.entity.Workshop;
import com.webapp.craictivity.service.ParticipantService;
import com.webapp.craictivity.service.WorkshopService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
        Participant participant = new Participant(); //object created to hold participant form data
        model.addAttribute("participant", participant);
        return "create_participant";
    }

    @PostMapping("/participant/{id}")
    public String saveParticipant(@PathVariable Long id, @ModelAttribute("participant") Participant participant){
        //get the workshop from the database by id
        Workshop existingWorkshop = workshopService.getWorkshopById(id);
        //add participant references to workshop
        existingWorkshop.getParticipants().add(participant);
        //add workshop references to participant
        participant.getWorkshops().add(existingWorkshop);
        //save to the database
        workshopService.updateWorkshop(existingWorkshop); //Workshop entity is the owning side of the relationship ManyToMany with Participant
        //once form is submitted, redirect to the homepage
        return "redirect:/";
    }
}
