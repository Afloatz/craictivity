package com.webapp.craictivity.controller;

import com.webapp.craictivity.entity.Workshop;
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

    public WorkshopController(WorkshopService workshopService) {
        super();
        this.workshopService = workshopService;
    }

    //home page --> should I put it in that controller or have its own controller?
    //the method displayWorkshops is basically the same as listWorkshops , could I combine both?
    @GetMapping("/")
    public String displayWorkshops(Model model){
        model.addAttribute("workshops", workshopService.getAllWorkshops());
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
        return "create_workshop";
    }

    @PostMapping("/workshops")
    public String saveWorkshop(@ModelAttribute("workshop") Workshop workshop){
        //save the workshop object to the database
        workshopService.saveWorkshop(workshop);
        return "redirect:/workshops";
    }

    @GetMapping("/workshops/edit/{id}")
    public String editWorkshopForm(@PathVariable Long id, Model model){
        model.addAttribute("workshop", workshopService.getWorkshopById(id));
        return "edit_workshop";
    }

    @PostMapping("/workshops/{id}")
    public String updateWorkshop(@PathVariable Long id, @ModelAttribute("workshop") Workshop workshop){
        //get the workshop from the database by id
        Workshop existingWorkshop = workshopService.getWorkshopById(id);
        existingWorkshop.setId(id);
        existingWorkshop.setTitle(workshop.getTitle());
        existingWorkshop.setDate(workshop.getDate());
        existingWorkshop.setDuration(workshop.getDuration());
        existingWorkshop.setPrice(workshop.getPrice());

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

}
