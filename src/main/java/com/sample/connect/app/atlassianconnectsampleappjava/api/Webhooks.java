package com.sample.connect.app.atlassianconnectsampleappjava.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.connect.app.atlassianconnectsampleappjava.model.Log;
import com.sample.connect.app.atlassianconnectsampleappjava.model.Tenant;
import com.sample.connect.app.atlassianconnectsampleappjava.repository.LogRepository;
import com.sample.connect.app.atlassianconnectsampleappjava.repository.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Instant;

@RestController
@RequestMapping("/webhooks")
public class Webhooks {
    @Autowired
    TenantRepository tenantRepository;
    @Autowired
    LogRepository logRepository;

    private void saveLog(String jsonBody, String operation) throws MalformedURLException, JsonProcessingException {
        JsonNode mapper = new ObjectMapper().readValue(jsonBody, JsonNode.class);
        String selfUrl = mapper.get("user").get("self").textValue();
        String domain = new URL(selfUrl).getHost();
        String protocol = new URL(selfUrl).getProtocol();
        String host =  String.format("%s://%s", protocol, domain);

        Tenant tenant = tenantRepository.findByHost(host).get(0);
        Log log = new Log(tenant.getId(), Instant.now().toString() + " Issue " + operation + "!", jsonBody);

        logRepository.save(log);
    }

    @PostMapping("/jira/issue-created")
    public ResponseEntity<String> issueCreated(@RequestBody String body) throws MalformedURLException, JsonProcessingException {
        saveLog(body, "Created");
        return ResponseEntity.ok().build();
    }

    @PostMapping("/jira/issue-updated")
    public ResponseEntity<String> issueUpdated(@RequestBody String body) throws MalformedURLException, JsonProcessingException {
        saveLog(body, "Updated");
        return ResponseEntity.ok().build();
    }

    @PostMapping("/jira/issue-deleted")
    public ResponseEntity<String> issueDeleted(@RequestBody String body) throws MalformedURLException, JsonProcessingException {
        saveLog(body, "Deleted");
        return ResponseEntity.ok().build();
    }
}
