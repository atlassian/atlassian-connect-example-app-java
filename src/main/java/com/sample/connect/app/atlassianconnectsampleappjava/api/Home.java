package com.sample.connect.app.atlassianconnectsampleappjava.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Home {

    @GetMapping("/")
    public String home() {
        // TODO: Add templates for view
        return "This is the home page";
    }
}
