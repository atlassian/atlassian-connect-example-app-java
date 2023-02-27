package com.sample.connect.app.atlassianconnectsampleappjava.api;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class Home {

    @GetMapping("/")
    public ModelAndView home(ModelMap model) {
        model.put("index", "Home Page");
        model.put("body", "You in the home page!");

        return new ModelAndView("home", model);
    }
}
