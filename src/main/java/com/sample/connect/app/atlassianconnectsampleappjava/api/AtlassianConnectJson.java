package com.sample.connect.app.atlassianconnectsampleappjava.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;

@RestController
public class AtlassianConnectJson {
    @GetMapping("/atlassian-connect.json")
    public JsonNode atlassianConnect() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File("atlassian-connect.json"), JsonNode.class);
    }

    @GetMapping("/config")
    public ModelAndView config(ModelMap model) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        JsonNode jsonNode = mapper.readValue(new File("atlassian-connect.json"), JsonNode.class);
        String jsonString = mapper.writeValueAsString(jsonNode);

        model.put("index", "Connect app descriptor");
        model.put("config", jsonString);

        return new ModelAndView("config", model);
    }
}
