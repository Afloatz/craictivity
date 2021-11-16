package com.webapp.craictivity.service;


import com.stripe.model.Charge;

public interface StripeService {

    public Charge chargeCard(String token, double amount);
}
