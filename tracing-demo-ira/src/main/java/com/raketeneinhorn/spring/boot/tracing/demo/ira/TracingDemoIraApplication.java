package com.raketeneinhorn.spring.boot.tracing.demo.ira;

import com.raketeneinhorn.spring.boot.tracing.demo.configuration.TracingDemoConfigurationProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class TracingDemoIraApplication {

	public static void main(String[] args) {
		SpringApplication.run(TracingDemoIraApplication.class, args);
	}

	@Slf4j
	@RequiredArgsConstructor
	@RestController
	public static class Controller {

		private final TracingDemoConfigurationProperties configuration;
		private final RestTemplate restTemplate;

		@GetMapping("/rot")
		public Map<String,Object> rot() {
			log.info("ira-rot");
			return Map.of("ira", "ira-rot");
		}

		@GetMapping("/gelb")
		public Map<String,Object> gelb() {
			log.info("ira-gelb");

			Map<String,Object> result = new HashMap<>();
			result.put("ira", "ira-gelb");
			result.put("rohan", restTemplate.getForObject(configuration.rohan().resolve("/gelb"), Map.class));

			return result;
		}

	}

}
