package com.kunal.design_pattern_lab.Controller;

import com.kunal.design_pattern_lab.StrategyPattern.NotificationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notify")
public class NotificationController {

    @Autowired
    private NotificationContext context;

    @PostMapping
    public String notify(@RequestParam String channel,
                         @RequestParam String to,
                         @RequestParam String message){
        context.send(channel, to, message);
        return "Notification send via " + channel;
    }
}
