package com.raketeneinhorn.spring.boot.tracing.demo.sidra;

import com.raketeneinhorn.spring.boot.tracing.demo.configuration.TracingDemoConfigurationProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class TracingDemoSidraApplication {

	public static void main(String[] args) {
		SpringApplication.run(TracingDemoSidraApplication.class, args);
	}

	@Slf4j
	@RequiredArgsConstructor
	@RestController
	public static class Controller {

		private final TracingDemoConfigurationProperties configuration;
		private final RestTemplate restTemplate;

		@GetMapping("/rot")
		public Map<String,Object> rot() {
			log.info("sidra-rot");

			Map<String,Object> result = new HashMap<>();
			result.put("sidra", "sidra-rot");
			result.put("ira", restTemplate.getForObject(configuration.ira().resolve("/rot"), Map.class));
			result.put("rohan", restTemplate.getForObject(configuration.rohan().resolve("/rot"), Map.class));

			return result;
		}

		@GetMapping("/gelb")
		public Map<String,Object> gelb() {
			log.info("sidra-gelb");

			Map<String,Object> result = new HashMap<>();
			result.put("sidra", "sidra-gelb");
			result.put("ira", restTemplate.getForObject(configuration.ira().resolve("/gelb"), Map.class));

			return result;
		}

		@GetMapping("/blau")
		@NewSpan
		public Map<String,Object> blau() {
			log.info("sidra-blau");
			return Map.of("blau", "sidra-blau");
		}

	}

}
