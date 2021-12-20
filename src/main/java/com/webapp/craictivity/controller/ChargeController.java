package com.webapp.craictivity.controller;

import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.webapp.craictivity.entity.ChargeRequest;
import com.webapp.craictivity.entity.Enrollment;
import com.webapp.craictivity.service.EnrollmentService;
import com.webapp.craictivity.service.StripeService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Log
@Controller
public class ChargeController {

    @Autowired
    StripeService paymentsService;

    private EnrollmentService enrollmentService;

    public ChargeController(EnrollmentService enrollmentService) {
        super();
        this.enrollmentService = enrollmentService;
    }

    //payment processing
    @PostMapping("/charge")
    public String charge(@RequestParam(name = "enrollmentId") Long enrollmentId, ChargeRequest chargeRequest, Model model) throws StripeException {
        chargeRequest.setDescription("Example charge");
        chargeRequest.setCurrency(ChargeRequest.Currency.EUR);
        //retrieve the corresponding enrollment (workshop-participant)
        Enrollment enrollment = enrollmentService.load(enrollmentId);
        Charge charge = paymentsService.charge(chargeRequest);
        model.addAttribute("id", charge.getId());
        model.addAttribute("status", charge.getStatus());
        // if the payment is successful, then set the enrollment as paid
        if (charge.getStatus().equals("succeeded")) {
            enrollment.setPaid(true);
            //chargeRequest.getEnrollment().setPaid(true);
            enrollmentService.saveEnrollment(enrollment);
        }
        //model.addAttribute("chargeId", charge.getId());
        model.addAttribute("balance_transaction", charge.getBalanceTransaction());
        return "result";
    }

    @ExceptionHandler(StripeException.class)
    public String handleError(Model model, StripeException ex) {
        model.addAttribute("error", ex.getMessage());
        return "result";
    }
}
