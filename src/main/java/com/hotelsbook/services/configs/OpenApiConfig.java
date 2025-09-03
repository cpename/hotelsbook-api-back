package com.hotelsbook.services.configs;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(
		info = @Info(
			title = "Hotels Book",
			version = "1.0.0",
			description = "This a web application for booking hotels"
		)
)
public class OpenApiConfig {

}
