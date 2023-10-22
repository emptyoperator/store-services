package org.leniv.distributed.systems.order.config;

import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

import org.leniv.distributed.systems.order.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
@EnableMethodSecurity
class SecurityConfig {
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserService userService(@Value("${user.service.base.url}") String baseUrl,
                            @Value("${admin.user.username}") String username,
                            @Value("${admin.user.password}") String password) {
        WebClient webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeaders(header -> header.setBasicAuth(username, password))
                .build();
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(webClient))
                .build();
        return httpServiceProxyFactory.createClient(UserService.class);
    }

    @Bean
    UserDetailsService userDetailsService(UserService service) {
        return username -> {
            try {
                return service.findByUsername(username);
            } catch (WebClientResponseException e) {
                throw new UsernameNotFoundException("User with username '%s' not found".formatted(username));
            }
        };
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .requestMatchers(antMatcher("/h2-console/**")).permitAll()
                        .requestMatchers(HttpMethod.GET, "/products/**").permitAll()
                        .requestMatchers("/products/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .headers(headers -> headers.frameOptions().disable())
                .httpBasic(withDefaults())
                .csrf().disable();
        return http.build();
    }
}
