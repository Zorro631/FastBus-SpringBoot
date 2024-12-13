package hwg.bachelor.fastbusspringboot.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

            http.csrf().disable() // Deaktiviert CSRF für Testzwecke, in Produktion aktivieren!
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/completeOrder1","/orderConfirmation1", "/connectionDetails1").authenticated()
                        .anyRequest().permitAll() // Alle Endpunkte erfordern Authentifizierung
                )
                .formLogin(form -> form
//                        .loginPage("/login") // Optional: Benutzerdefinierte Login-Seite
                        .defaultSuccessUrl("/home") // Nach erfolgreichem Login
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Passwort-Encoder für sichere Speicherung
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
