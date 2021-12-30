package com.webapp.craictivity.controller;

import com.webapp.craictivity.entity.Enrollment;
import com.webapp.craictivity.entity.Participant;
import com.webapp.craictivity.entity.Workshop;
import com.webapp.craictivity.service.WorkshopService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ParticipantController {

    private final WorkshopService workshopService;

    public ParticipantController(final WorkshopService workshopService) {
        this.workshopService = workshopService;
    }

    //display list of participants for a specific workshop
    @GetMapping("/participants/{workshopId}")
    public String displayParticipants(@PathVariable Long workshopId, Model model){
        final Workshop workshop = workshopService.getWorkshopById(workshopId);

        final List<Participant> participants = new ArrayList<>();
        for(final Enrollment enrollment : workshop.getEnrollments()){
            participants.add(enrollment.getParticipant());
        }

        model.addAttribute("participants", participants);
        return "participants_list";
    }
}
