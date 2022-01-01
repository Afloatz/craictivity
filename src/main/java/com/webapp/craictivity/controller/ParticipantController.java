package com.webapp.craictivity.controller;

import com.webapp.craictivity.entity.Enrollment;
import com.webapp.craictivity.entity.Participant;
import com.webapp.craictivity.entity.Workshop;
import com.webapp.craictivity.service.WorkshopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ParticipantController {

    @Autowired
    private WorkshopService workshopService;

    //display list of participants for a specific workshop
    @GetMapping("/participants/{workshopId}")
    public String displayParticipants(@PathVariable Long workshopId, Model model){
        final Workshop workshop = workshopService.getWorkshopById(workshopId);

        //First check if the set is not empty
        if(!CollectionUtils.isEmpty(workshop.getEnrollments())){
            final List<Participant> participants = new ArrayList<>();
            for(final Enrollment enrollment : workshop.getEnrollments()){
                participants.add(enrollment.getParticipant());
            }

            model.addAttribute("participants", participants);
        }
        return "participants_list";
    }
}
