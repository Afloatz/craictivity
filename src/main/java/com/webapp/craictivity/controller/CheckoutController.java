package com.webapp.craictivity.controller;

import com.webapp.craictivity.entity.ChargeRequest;
import com.webapp.craictivity.entity.Workshop;
import com.webapp.craictivity.service.WorkshopService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CheckoutController {

    @Value("${STRIPE_PUBLIC_KEY}")
    private String stripePublicKey;
    private WorkshopService workshopService;

    public CheckoutController(WorkshopService workshopService) {
        super();
        this.workshopService = workshopService;
    }

    @RequestMapping("/checkout/{id}")
    public String checkout(@PathVariable Long id, Model model) {
        Workshop existingWorkshop = workshopService.getWorkshopById(id);
        model.addAttribute("amount", existingWorkshop.getPrice()*100); // in cents
        model.addAttribute("stripePublicKey", stripePublicKey);
        model.addAttribute("currency", ChargeRequest.Currency.EUR);
        return "checkout";
    }
}
