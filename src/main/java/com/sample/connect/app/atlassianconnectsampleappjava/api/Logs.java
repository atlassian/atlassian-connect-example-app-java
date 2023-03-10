package com.sample.connect.app.atlassianconnectsampleappjava.api;

import com.sample.connect.app.atlassianconnectsampleappjava.model.Log;
import com.sample.connect.app.atlassianconnectsampleappjava.model.Tenant;
import com.sample.connect.app.atlassianconnectsampleappjava.repository.LogRepository;
import com.sample.connect.app.atlassianconnectsampleappjava.repository.TenantRepository;
import com.sample.connect.app.atlassianconnectsampleappjava.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/logs")
public class Logs {
    @Autowired
    LogRepository logRepository;

    @Autowired
    TenantRepository tenantRepository;

    @GetMapping("/webhooks")
    public ModelAndView home(@RequestParam Map<String, String> params, ModelMap model) throws Exception {
        String host = params.get("xdm_e");
        String jwt = params.get("jwt");

        Tenant tenant = tenantRepository.findByHost(host).get(0);

        // TODO: Verify the jwt token using atlassian-jwt

        List<Log> logs = logRepository.findByTenantId(tenant.getId());

        model.put("index", "Webhooks Page");
        model.put("logs", logs);

        return new ModelAndView("webhooksLogs", model);
    }
}
