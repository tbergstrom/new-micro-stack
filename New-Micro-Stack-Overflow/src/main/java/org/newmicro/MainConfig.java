package org.newmicro;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class MainConfig {

    @Bean
    public PasswordEncoder getPasswordEncoder() {return new BCryptPasswordEncoder();}

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        // Configure CORS globally vs controller-by-controller
        // Can be combined with @CrossOrigin

        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000")
                        .allowedMethods("*");
            }
        };
    }
}
