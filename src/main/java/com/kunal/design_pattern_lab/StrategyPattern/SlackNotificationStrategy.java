package com.kunal.design_pattern_lab.StrategyPattern;

import org.springframework.stereotype.Component;

@Component("slack")
public class SlackNotificationStrategy implements NotificationStrategy {

    @Override
    public void send(String to, String message) {
        System.out.println("Sending Slack message to " + to + ": " + message);
    }
}
