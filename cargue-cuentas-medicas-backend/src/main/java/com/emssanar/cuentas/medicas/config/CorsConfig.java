package com.emssanar.cuentas.medicas.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig  implements WebMvcConfigurer {

	    @Override
	    public void addCorsMappings(CorsRegistry registry) {
	        registry.addMapping("/**")
	            .allowedOrigins("http://localhost:4200") // Reemplaza con tu URL de Angular
	            .allowedMethods("GET", "POST", "PUT", "DELETE")
	            .allowedHeaders("*")
	            .allowCredentials(true);
	    }
	}
