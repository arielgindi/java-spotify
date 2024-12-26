package spotify.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "spotify") // סורק את כל המחלקות בחבילה "spotify"
public class AppConfig {
}
