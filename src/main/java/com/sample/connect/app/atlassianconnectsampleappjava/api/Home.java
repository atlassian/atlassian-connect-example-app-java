package com.sample.connect.app.atlassianconnectsampleappjava.api;

import com.sample.connect.app.atlassianconnectsampleappjava.model.Tenant;
import com.sample.connect.app.atlassianconnectsampleappjava.repository.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;


@RestController
public class Home {
    @Autowired
    TenantRepository tenantRepository;

    @GetMapping("/")
    public ModelAndView home(ModelMap model) {
        model.put("index", "Home Page");
        model.put("body", "You in the home page!");

        return new ModelAndView("home", model);
    }

    @GetMapping("/checkdb")
    public ModelAndView db(ModelMap model) {
        model.put("index", "Home Page");
        model.put("body", "You in the db page!");

        Long identifier = new Timestamp(System.currentTimeMillis()).getTime();

        Tenant newTenant = new Tenant("https://mytenant" + identifier + ".com", "sharedSecret-"+identifier, "clientKey-" + identifier);

        tenantRepository.save(newTenant);


        return new ModelAndView("home", model);
    }
}
