package com.raketeneinhorn.spring.boot.tracing.demo.rohan;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@SpringBootApplication
public class TracingDemoRohanApplication {

	public static void main(String[] args) {
		SpringApplication.run(TracingDemoRohanApplication.class, args);
	}

	@Slf4j
	@RestController
	public static class Controller {

		@GetMapping("/rot")
		public Map<String,Object> rot() {
			log.info("rohan-rot");
			return Map.of("rohan", "rohan-rot");
		}

		@GetMapping("/gelb")
		public Map<String,Object> gelb() {
			log.info("rohan-gelb");
			return Map.of("rohan", "rohan-gelb");
		}
	}

}
