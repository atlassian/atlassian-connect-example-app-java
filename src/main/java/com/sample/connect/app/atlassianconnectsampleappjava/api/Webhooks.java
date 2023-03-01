package com.sample.connect.app.atlassianconnectsampleappjava.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/webhooks")
public class Webhooks {
    @PostMapping("/jira/issue-created")
    public String issueCreated(@RequestBody Map<String, Object> body) {
        // TODO: Save this webhook to DB
        System.out.println("Jira Issue Created : " + body.toString());
        return "200";
    }

    @PostMapping("/jira/issue-updated")
    public String issueUpdated(@RequestBody Map<String, Object> body) {
        // TODO: Save this webhook to DB
        System.out.println("Jira Issue Updated : " + body.toString());
        return "200";
    }

    @PostMapping("/jira/issue-deleted")
    public String issueDeleted(@RequestBody Map<String, Object> body) {
        // TODO: Save this webhook to DB
        System.out.println("Jira Issue Deleted : " + body.toString());
        return "200";
    }
}
