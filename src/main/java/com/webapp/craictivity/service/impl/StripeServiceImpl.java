package com.webapp.craictivity.service.impl;

import com.stripe.model.Charge;
import com.webapp.craictivity.service.StripeService;
import org.springframework.stereotype.Service;

@Service
public class StripeServiceImpl implements StripeService {

    @Override
    public Charge chargeCard(String token, double amount) {
        return null;
    }
}
