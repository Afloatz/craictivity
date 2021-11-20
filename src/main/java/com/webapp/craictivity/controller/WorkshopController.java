package com.webapp.craictivity.controller;

import com.webapp.craictivity.entity.Participant;
import com.webapp.craictivity.entity.Workshop;
import com.webapp.craictivity.service.InstructorService;
import com.webapp.craictivity.service.WorkshopService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WorkshopController {

    private WorkshopService workshopService;
    private InstructorService instructorService;

    public WorkshopController(WorkshopService workshopService, InstructorService instructorService) {
        super();
        this.workshopService = workshopService;
        this.instructorService = instructorService;
    }

    //home page --> should I put it in that controller or have its own controller?
    //the method displayWorkshops is basically the same as listWorkshops , could I combine both?
    //Display all the workshops + has a search function
    //keyword is coming from the url parameter
    @GetMapping("/")
    public String displayWorkshops(Model model, String keyword){
        if (keyword != null){
            model.addAttribute("workshops", workshopService.findByKeyword(keyword));
        } else {
            model.addAttribute("workshops", workshopService.getAllWorkshops());
        }
        return "home";
    }

    //admin page to manage the workshops
    @GetMapping("/workshops")
    public String listWorkshops(Model model){
        model.addAttribute("workshops", workshopService.getAllWorkshops());
        return "workshops";
    }

    @GetMapping("/workshops/new")
    public String createWorkshopForm(Model model){
        Workshop workshop = new Workshop(); //object created to hold workshop form data
        model.addAttribute("workshop", workshop);
        model.addAttribute("instructors", instructorService.getAllInstructors());
        return "create_workshop";
    }

    @PostMapping("/workshops")
    public String saveWorkshop(@ModelAttribute("workshop") Workshop workshop){
        //save the workshop object to the database
        workshopService.saveWorkshop(workshop);
        return "redirect:/workshops";
    }

    //Edit an existing workshop
    @GetMapping("/workshops/edit/{id}")
    public String editWorkshopForm(@PathVariable Long id, Model model){
        model.addAttribute("workshop", workshopService.getWorkshopById(id));
        model.addAttribute("instructors", instructorService.getAllInstructors());
        return "edit_workshop";
    }

    @PostMapping("/workshops/{id}")
    public String updateWorkshop(@PathVariable Long id, @ModelAttribute("workshop") Workshop workshop){
        //get the workshop from the database by id
        Workshop existingWorkshop = workshopService.getWorkshopById(id);
        //populate the existing workshop object with the values taken from the form
        existingWorkshop.setId(id);
        existingWorkshop.setTitle(workshop.getTitle());
        existingWorkshop.setDate(workshop.getDate());
        existingWorkshop.setDuration(workshop.getDuration());
        existingWorkshop.setPrice(workshop.getPrice());
        existingWorkshop.setInstructor(workshop.getInstructor());
        //save updated workshop object
        workshopService.updateWorkshop(existingWorkshop);
        return "redirect:/workshops";
    }

    //handle workshop delete request
    @GetMapping("/workshops/{id}")
    public String deleteWorkshop(@PathVariable Long id){
        workshopService.deleteWorkshopById(id);
        return "redirect:/workshops";
    }

    //display the form to register to a specific workshop
    @GetMapping("/register/workshop/{id}")
    public String createParticipantForm(@PathVariable Long id, Model model){
        model.addAttribute("workshop", workshopService.getWorkshopById(id));
        Participant participant = new Participant(); //object created to hold participant form data
        model.addAttribute("participant", participant);
        return "create_participant";
    }

    //handle the form submission to register a participant to a specific workshop
    @PostMapping("/register/participant/workshop/{id}")
    public String saveParticipant(@PathVariable Long id, @ModelAttribute("participant") Participant participant){
        //get the workshop from the database by id
        Workshop existingWorkshop = workshopService.getWorkshopById(id);
        participant.setId(null);
        existingWorkshop.getParticipants().add(participant);
        //add workshop references to participant
        participant.getWorkshops().add(existingWorkshop);
        //save to the database
        workshopService.updateWorkshop(existingWorkshop);
        //once form is submitted, redirect to the checkout page for payment
        return "redirect:/checkout/{id}";
    }

}
