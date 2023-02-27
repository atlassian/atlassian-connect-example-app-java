package com.sample.connect.app.atlassianconnectsampleappjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class AtlassianConnectSampleAppJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtlassianConnectSampleAppJavaApplication.class, args);
	}

	@GetMapping("/")
	public String home() {
		return "This is the home page for the sample connect app!";
	}
}
