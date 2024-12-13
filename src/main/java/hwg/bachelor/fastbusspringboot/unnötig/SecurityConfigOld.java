//package hwg.bachelor.fastbusspringboot.security;
//
//import hwg.bachelor.fastbusspringboot.login.LoginService;
//import hwg.bachelor.fastbusspringboot.model.Account;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//    @Autowired
//    private final LoginService loginService;
//
//
//    @Bean
//    public UserDetailsService userDetailsService(){
//        return loginService;
//    }
//
//    @Bean
//    public AuthenticationProvider authenticationProvider(){
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setUserDetailsService(appUserService);
//        provider.setPasswordEncoder(passwordEncoder());
//        return provider;
//    }
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws  Exception{
//        return  httpSecurity
//                .csrf(AbstractHttpConfigurer::disable)
//                .formLogin(httpForm ->{
//                    httpForm
//                            .loginPage("/login").permitAll();
//                })
//                .authorizeHttpRequests(registry ->{
//                    registry.requestMatchers("/register","/css/**" ,"/js/**").permitAll();
//                    registry.anyRequest().authenticated();
//                })
//                .build();
//    }
//}
