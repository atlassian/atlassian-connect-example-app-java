package com.sample.connect.app.atlassianconnectsampleappjava.api;

import com.sample.connect.app.atlassianconnectsampleappjava.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/logs")
public class Logs {
    @Autowired
    LogRepository logRepository;

    @GetMapping("/webhooks")
    public ModelAndView home(ModelMap model) {
        model.put("index", "Webhooks Page");

        // TODO: Need to get logs for individual apps
        model.put("logs", logRepository.findAll());

        return new ModelAndView("webhooksLogs", model);
    }
}
