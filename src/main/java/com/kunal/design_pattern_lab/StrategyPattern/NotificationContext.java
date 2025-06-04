package com.kunal.design_pattern_lab.StrategyPattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class NotificationContext {

    @Autowired
    private ApplicationContext context;

    public void send(String channel, String to, String message){
        NotificationStrategy strategy = (NotificationStrategy) context.getBean(channel);
        strategy.send(to, message);
    }
}
