package com.example.Real_Estate_Platform.Security;//package com.example.Real_Estate_Platform.Security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends GlobalMethodSecurityConfiguration {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authorize) -> authorize.requestMatchers(HttpMethod.POST,"/mediatorRegister").hasRole("MEDIATOR")
//                .requestMatchers(HttpMethod.POST,"/registers").hasRole("MEDIATOR")
//                .requestMatchers(HttpMethod.POST,"/register").hasRole("MEDIATOR")
                .requestMatchers(HttpMethod.GET,"/login").hasRole("MEDIATOR")
//                .requestMatchers(HttpMethod.GET,"/loginMediator").hasRole("MEDIATOR")
//                .requestMatchers(HttpMethod.POST,"/viewAllSeller").hasRole("MEDIATOR")
//                .requestMatchers(HttpMethod.POST,"/viewBuyer").hasRole("MEDIATOR")
                .requestMatchers(HttpMethod.POST,"/sellerRegister").hasRole("SELLER")
//                .requestMatchers(HttpMethod.POST,"/regSeller").hasRole("SELLER")
//                .requestMatchers(HttpMethod.POST,"/registerSellers").hasRole("SELLER")
//                .requestMatchers(HttpMethod.GET,"/logins").hasRole("SELLER")
//                .requestMatchers(HttpMethod.GET,"/loginSeller").hasRole("SELLER")
//                .requestMatchers(HttpMethod.GET,"/main").hasRole("SELLER")
//                .requestMatchers(HttpMethod.POST,"/addProperty").hasRole("SELLER")
//                .requestMatchers(HttpMethod.POST,"/saveProperty").hasRole("SELLER")
//                .requestMatchers(HttpMethod.GET,"/viewProperties").hasRole("SELLER")
//                .requestMatchers(HttpMethod.GET,"/viewProperty").hasRole("SELLER")
//                .requestMatchers(HttpMethod.PUT,"/updatePropertyForm").hasRole("SELLER")
//                .requestMatchers(HttpMethod.PUT,"/updateProperty").hasRole("SELLER")
//                .requestMatchers(HttpMethod.DELETE,"/confirmDelete").hasRole("SELLER")
//                .requestMatchers(HttpMethod.DELETE,"/deleteProperty").hasRole("SELLER")
//                .requestMatchers(HttpMethod.GET,"/buyer").hasRole("BUYER")
                .requestMatchers(HttpMethod.POST,"/buyerRegister").hasRole("BUYER")
//                .requestMatchers(HttpMethod.POST,"/regBuyer").hasRole("BUYER")
//                .requestMatchers(HttpMethod.POST,"/registerBuyers").hasRole("BUYER")
//                .requestMatchers(HttpMethod.GET,"/loginB").hasRole("BUYER")
//                .requestMatchers(HttpMethod.GET,"/loginBuyer").hasRole("BUYER")
//                .requestMatchers(HttpMethod.GET,"/searching").hasRole("BUYER")
//                .requestMatchers(HttpMethod.GET,"/search").hasRole("BUYER")
//                .requestMatchers(HttpMethod.POST,"/buy").hasRole("BUYER")
                .anyRequest().permitAll());
        http.httpBasic(Customizer.withDefaults());
        http.csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }
}
