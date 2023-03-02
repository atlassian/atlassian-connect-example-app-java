package com.sample.connect.app.atlassianconnectsampleappjava.api;

import com.sample.connect.app.atlassianconnectsampleappjava.model.Tenant;
import com.sample.connect.app.atlassianconnectsampleappjava.repository.LogRepository;
import com.sample.connect.app.atlassianconnectsampleappjava.repository.TenantRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/events")
public class Events {
    @Autowired
    TenantRepository tenantRepository;

    @Autowired
    LogRepository logRepository;

    @PostMapping("/installed")
    public ResponseEntity<String> installed(@RequestBody Map<String, String> body) throws Exception {
        try {
            Tenant newTenant = new Tenant(body.get("baseUrl"), body.get("sharedSecret"), body.get("clientKey"));
            tenantRepository.save(newTenant);

            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    @PostMapping("/uninstalled")
    @Transactional
    public ResponseEntity<String> uninstalled(@RequestBody Map<String, String> body) throws Exception {
        try {
            List<Tenant> tenant = tenantRepository.findByHost(body.get("baseUrl"));
            tenantRepository.delete(tenant.get(0));
            logRepository.deleteByTenantId(tenant.get(0).getId());

            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }
}
