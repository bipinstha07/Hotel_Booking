package com.hotelbooking.springBoot.service.payment;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class PaymentImp implements PaymentInterface{
    @Value("${stripe.secretKey}")
    private String secretKey;

    //stripe -API
    //-> productName , amount , quantity , currency
    //-> return sessionId and url




}
