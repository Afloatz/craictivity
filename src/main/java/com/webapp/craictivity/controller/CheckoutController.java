package com.webapp.craictivity.controller;

import com.webapp.craictivity.CustomUserDetails;
import com.webapp.craictivity.entity.ChargeRequest;
import com.webapp.craictivity.entity.Enrollment;
import com.webapp.craictivity.entity.Participant;
import com.webapp.craictivity.entity.Workshop;
import com.webapp.craictivity.service.EnrollmentService;
import com.webapp.craictivity.service.WorkshopService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CheckoutController {

    @Value("${STRIPE_PUBLIC_KEY}")
    private String stripePublicKey;
    private WorkshopService workshopService;
    private EnrollmentService enrollmentService;


    public CheckoutController(WorkshopService workshopService, EnrollmentService enrollmentService) {
        super();
        this.workshopService = workshopService;
        this.enrollmentService = enrollmentService;
    }

    @RequestMapping("/checkout/{id}")
    public String checkout(@PathVariable Long id, Model model) {
        Enrollment enrollment = enrollmentService.load(id);
        Workshop existingWorkshop = enrollment.getWorkshop();
        //Workshop existingWorkshop = workshopService.getWorkshopById(id);
        model.addAttribute("amount", existingWorkshop.getPrice()*100); // in cents
        model.addAttribute("stripePublicKey", stripePublicKey);
        model.addAttribute("currency", ChargeRequest.Currency.EUR);
        model.addAttribute("id", id);
        model.addAttribute("enrollmentId", enrollment.getId() );
        return "checkout" ;
    }
}
