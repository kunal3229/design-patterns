package com.kunal.design_pattern_lab.StrategyPattern;

import org.springframework.stereotype.Component;

@Component("sms")
public class SmsNotificationStrategy implements NotificationStrategy{

    @Override
    public void send(String to, String message) {
        System.out.println("Sending SMS to " + to + ": " + message);
    }
}
