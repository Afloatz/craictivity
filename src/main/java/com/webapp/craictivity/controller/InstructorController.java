package com.webapp.craictivity.controller;

import com.webapp.craictivity.entity.Instructor;
import com.webapp.craictivity.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class InstructorController {

    @Autowired
    private InstructorService instructorService;

    @GetMapping("/instructors/new")
    public String createWorkshopForm(Model model){
        model.addAttribute("instructor", new Instructor());
        return "create_instructor";
    }

    @PostMapping("/instructors/save")
    public String saveInstructor(Instructor instructor){
        instructorService.saveInstructor(instructor);
        return "redirect:/workshops";
    }


}
