package hwg.bachelor.fastbusspringboot;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:63342")  // Erlaube Anfragen von deinem Frontend
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // Erlaube die HTTP-Methoden
                .allowedHeaders("*")  // Erlaube alle Header
                .allowCredentials(true);  // Optional: Erlaube Cookies/Anmeldeinformationen
    }
}