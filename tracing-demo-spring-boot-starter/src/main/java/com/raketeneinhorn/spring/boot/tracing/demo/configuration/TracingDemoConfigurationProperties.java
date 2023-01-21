package com.raketeneinhorn.spring.boot.tracing.demo.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.net.URI;

@ConfigurationProperties(prefix = "tracing.demo")
@ConstructorBinding
public record TracingDemoConfigurationProperties(
            URI sidra,
            URI ira,
            URI rohan
    ) {
}
