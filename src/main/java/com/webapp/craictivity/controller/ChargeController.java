package com.webapp.craictivity.controller;

import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.webapp.craictivity.entity.ChargeRequest;
import com.webapp.craictivity.entity.Enrollment;
import com.webapp.craictivity.service.StripeService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Log
@Controller
public class ChargeController {

    @Autowired
    StripeService paymentsService;

    @PostMapping("/charge")
    public String charge(@PathVariable Long id, ChargeRequest chargeRequest, Model model) throws StripeException {
        chargeRequest.setDescription("Example charge");
        chargeRequest.setCurrency(ChargeRequest.Currency.EUR);
//        chargeRequest.setEnrollment( );
        Charge charge = paymentsService.charge(chargeRequest);
        model.addAttribute("id", charge.getId());
        model.addAttribute("status", charge.getStatus());
//        if (charge.getStatus() == "succeeded") {
//            chargeRequest.getEnrollment().setPaid(true);
//        }
        model.addAttribute("chargeId", charge.getId());
        model.addAttribute("balance_transaction", charge.getBalanceTransaction());
        return "result";
    }

    @ExceptionHandler(StripeException.class)
    public String handleError(Model model, StripeException ex) {
        model.addAttribute("error", ex.getMessage());
        return "result";
    }
}
