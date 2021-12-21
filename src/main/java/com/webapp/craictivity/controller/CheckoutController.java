package com.webapp.craictivity.controller;

import com.webapp.craictivity.entity.ChargeRequest;
import com.webapp.craictivity.entity.Enrollment;
import com.webapp.craictivity.entity.Workshop;
import com.webapp.craictivity.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CheckoutController {

    @Value("${STRIPE_PUBLIC_KEY}")
    private String stripePublicKey;
    private final EnrollmentService enrollmentService;


    public CheckoutController(final EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @RequestMapping("/checkout/{id}")
    public String checkout(@PathVariable Long id, Model model) {
        Enrollment enrollment = enrollmentService.load(id);
        Workshop existingWorkshop = enrollment.getWorkshop();
        model.addAttribute("amount", existingWorkshop.getPrice()*100); // in cents
        model.addAttribute("stripePublicKey", stripePublicKey);
        model.addAttribute("currency", ChargeRequest.Currency.EUR);
        model.addAttribute("id", id);
        model.addAttribute("enrollmentId", enrollment.getId() );
        return "checkout" ;
    }
}
