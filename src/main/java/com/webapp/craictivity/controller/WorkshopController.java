package com.webapp.craictivity.controller;

import com.webapp.craictivity.service.WorkshopService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WorkshopController {

    private WorkshopService workshopService;

    public WorkshopController(WorkshopService workshopService) {
        super();
        this.workshopService = workshopService;
    }

    @GetMapping("/workshops")
    public String listWorkshops(Model model){
        model.addAttribute("workshops", workshopService.getAllWorkshops());
        return "workshops";

    }
}
