package config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Adrian on 28/03/2016.
 */
@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {
    /*@Override
    public void addCorsMappings(CorsRegistry registry) {
        System.out.println("Adding CORS mapping to filter");
        registry.addMapping("*//**")
                .allowedOrigins("http://localhost:63343")
                .exposedHeaders("Access-Control-Allow-Origin, Access-Control-Allow-Credentials")
                .allowCredentials(true);
    }*/
}
