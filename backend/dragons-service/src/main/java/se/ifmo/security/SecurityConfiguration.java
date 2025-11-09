package se.ifmo.security;

import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

  @Bean
  public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
    http.csrf(AbstractHttpConfigurer::disable)
        .cors(
            cors ->
                cors.configurationSource(
                    request -> {
                      var corsConfiguration = new CorsConfiguration();
                      corsConfiguration.setAllowedOriginPatterns(List.of("*"));
                      corsConfiguration.setAllowedMethods(
                          List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                      corsConfiguration.setAllowedHeaders(List.of("*"));
                      corsConfiguration.setAllowCredentials(true);
                      return corsConfiguration;
                    }))
        .httpBasic(AbstractHttpConfigurer::disable)
        .sessionManagement(
            sessionManagement ->
                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .exceptionHandling(
            configurer ->
                configurer
                    .authenticationEntryPoint(
                        (request, response, exception) ->
                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED))
                    .accessDeniedHandler(
                        (request, response, exception) ->
                            response.setStatus(HttpServletResponse.SC_FORBIDDEN)))
        .authorizeHttpRequests(configurer -> configurer.anyRequest().permitAll())
        .anonymous(AbstractHttpConfigurer::disable);
    return http.build();
  }
}
