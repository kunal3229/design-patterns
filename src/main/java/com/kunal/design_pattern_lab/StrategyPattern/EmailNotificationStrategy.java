package com.kunal.design_pattern_lab.StrategyPattern;

import org.springframework.stereotype.Component;

@Component("email")
public class EmailNotificationStrategy implements NotificationStrategy{
    @Override
    public void send(String to, String message) {
        System.out.println("Sending Email to " + to + ": " + message);
    }
}
