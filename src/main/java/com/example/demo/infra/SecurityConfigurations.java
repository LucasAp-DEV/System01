package com.example.demo.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {
    @Autowired
    SecurityFilter securityFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable) //Desabilitar o csrf
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize //Autorizando as requisições a baixo
                        //Requisições user-
                        .requestMatchers(HttpMethod.POST, "/user/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/user/register").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/user/update").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/user/delete").permitAll()
                        .requestMatchers(HttpMethod.GET, "/user").permitAll()
                        .requestMatchers(HttpMethod.GET, "/user/id").permitAll()
                        //Requisições local-
                        .requestMatchers(HttpMethod.POST, "/local/register").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/local/update").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/local/delete").permitAll()
                        .requestMatchers(HttpMethod.GET, "/local/id").permitAll()
                        //Requisições contrato-
                        .requestMatchers(HttpMethod.POST, "/contrato/register").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/contrato/upadate").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/contrato/delete").permitAll()
                        //Requisições cidade-
                        .requestMatchers(HttpMethod.POST, "/cidade/register").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/cidade/update").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/cidade/delete").permitAll()
                        //Requisições image-
                        .requestMatchers(HttpMethod.POST, "/images/register").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/images/update").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/images/delete").permitAll()
                        .requestMatchers(HttpMethod.GET, "/images/list").permitAll()

                        .anyRequest().authenticated() //Deixando outras requisições permitidas
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
