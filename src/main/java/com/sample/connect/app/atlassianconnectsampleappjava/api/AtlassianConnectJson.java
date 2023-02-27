package com.sample.connect.app.atlassianconnectsampleappjava.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

@RestController
public class AtlassianConnectJson {

    @GetMapping("/atlassian-connect.json")
    public JsonNode atlassianConnect() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File("atlassian-connect.json"), JsonNode.class);
    }
}
