package com.raketeneinhorn.spring.boot.tracing.demo.ira;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.sleuth.instrument.web.mvc.TracingClientHttpRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableConfigurationProperties(TracingDemoIraApplication.Configuration.class)
public class TracingDemoIraApplication {

	public static void main(String[] args) {
		SpringApplication.run(TracingDemoIraApplication.class, args);
	}

	@Slf4j
	@RequiredArgsConstructor
	@RestController
	public static class Controller {

		private final Configuration configuration;
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
			result.put("rohan", restTemplate.getForObject(configuration.rohan.resolve("/gelb"), Map.class));

			return result;
		}

	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
		RestTemplate restTemplate = restTemplateBuilder.build();
		restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
		return restTemplate;
	}

	@ConfigurationProperties(prefix = "tracing.demo")
	@ConstructorBinding
	public record Configuration(
			URI sidra,
			URI ira,
			URI rohan
	) {
	}
}
