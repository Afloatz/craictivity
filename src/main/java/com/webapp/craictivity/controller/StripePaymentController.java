package com.webapp.craictivity.controller;

import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StripePaymentController {

    // Reading the value from the application.properties file
    @Value("${STRIPE_PUBLIC_KEY}")
    String stripePublicKey;

    @RequestMapping("/checkout")
    public String payment(Model model) throws StripeException {
        model.addAttribute("amount", 50 * 100); // In cents
        model.addAttribute("stripePublicKey", stripePublicKey);
        return "checkout";
    }
}
