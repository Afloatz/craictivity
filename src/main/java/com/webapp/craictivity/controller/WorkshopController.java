package com.webapp.craictivity.controller;

import com.webapp.craictivity.CustomUserDetails;
import com.webapp.craictivity.entity.Enrollment;
import com.webapp.craictivity.entity.Participant;
import com.webapp.craictivity.entity.Workshop;
import com.webapp.craictivity.service.EnrollmentService;
import com.webapp.craictivity.service.InstructorService;
import com.webapp.craictivity.service.WorkshopService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class WorkshopController {

    private final WorkshopService workshopService;
    private final InstructorService instructorService;
    private final EnrollmentService enrollmentService;

    public WorkshopController(final WorkshopService workshopService,
                              final InstructorService instructorService,
                              final EnrollmentService enrollmentService) {
        this.workshopService = workshopService;
        this.instructorService = instructorService;
        this.enrollmentService = enrollmentService;
    }

    //home page
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

    //Participant dashboard
    @GetMapping("/dashboard")
    public String displayDashboard(Model model, final Authentication authentication){
        //Retrieve User Information in Spring Security
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        Participant participant = customUserDetails.getParticipant();
        Set<Enrollment> enrollments = participant.getEnrollments();
        // Converting HashSet to ArrayList (because Set has no get method)
        List<Enrollment> list = new ArrayList<Enrollment>(enrollments);
        model.addAttribute("enrollments", list);
        List<Workshop> workshops = workshopService.getAllWorkshops();
        model.addAttribute("workshops", workshopService.getAllWorkshops());
        return "participant_dashboard";
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
        existingWorkshop.setTime(workshop.getTime());
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

    //handle the form submission to register a participant to a specific workshop
    @PostMapping("/register/participant/workshop/{id}")
    public String saveParticipant(@PathVariable Long id, final Authentication authentication){
        //get the workshop from the database by id
        Workshop existingWorkshop = workshopService.getWorkshopById(id);
        //Retrieve User Information in Spring Security
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        Participant participant = customUserDetails.getParticipant();
        Enrollment enrollment = new Enrollment(participant, existingWorkshop, false);
        enrollmentService.saveEnrollment(enrollment);
        Long enrollmentId = enrollment.getId();
        //once form is submitted, redirect to the checkout page for payment
        return "redirect:/checkout/" + enrollmentId;
    }

}
