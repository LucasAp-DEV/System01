package demo.TCC.infra;

import lombok.RequiredArgsConstructor;
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

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    public final SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable) //Desabilitar o csrf
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize //Autorizando as requisições a baixo
                        //Requisições user-
                        .requestMatchers(HttpMethod.POST, "/user/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/user/register").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/user/update/{id}").hasRole("LOCADOR")
                        .requestMatchers(HttpMethod.GET, "/user/list").permitAll()
                        .requestMatchers(HttpMethod.GET, "/user/{id}").permitAll()
                        //Requisições local-
                        .requestMatchers(HttpMethod.POST, "/local/register").hasRole("LOCADOR")
                        .requestMatchers(HttpMethod.PUT, "/local/update/{id}").hasRole("LOCADOR")
//                        .requestMatchers(HttpMethod.DELETE, "/local/delete/{id}").hasRole("LOCADOR")
                        .requestMatchers(HttpMethod.GET, "/local/list").permitAll()
                        .requestMatchers(HttpMethod.GET, "/local/list/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/local/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/local/filter").permitAll()
                        //Requisições contrato-
                        .requestMatchers(HttpMethod.POST, "/contrato/register").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/contrato/update/{id}").permitAll()
//                        .requestMatchers(HttpMethod.DELETE, "/contrato/delete").permitAll()
                        .requestMatchers(HttpMethod.GET, "/contrato/list").permitAll()
                        .requestMatchers(HttpMethod.GET, "/contrato/user/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/contrato/{id}").permitAll()
                        //Requisições cidade-
//                        .requestMatchers(HttpMethod.POST, "/cidade/register").hasRole("LOCADOR")
//                        .requestMatchers(HttpMethod.DELETE, "/cidade/delete").hasRole("LOCADOR")
                        .requestMatchers(HttpMethod.GET, "/cidade/list").hasRole("LOCADOR")
                        //Requisições image-
                        .requestMatchers(HttpMethod.POST, "/images/register").permitAll()
//                        .requestMatchers(HttpMethod.DELETE, "/images/delete/{id}").hasRole("LOCADOR")
                        .requestMatchers(HttpMethod.GET, "/images/list").permitAll()
                        //Requisições feedback-
                        .requestMatchers(HttpMethod.POST, "/feedback/register").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/feedback/delete/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/feedback/list").permitAll()
                        //Requisições mercadoPago-
                        .requestMatchers(HttpMethod.POST, "/api/payments/create").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/payments/response").permitAll()

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
