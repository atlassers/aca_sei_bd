package it.euris.academy.cinema.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Busterna Davide
 *
 * @since 2021-09-24
 */

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("*").allowedMethods("OPTIONS", "GET", "PUT", "POST", "DELETE", "PATCH")
        .allowedOrigins("*").allowedHeaders("*");
  }
}
