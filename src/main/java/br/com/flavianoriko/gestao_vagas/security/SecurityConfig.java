package br.com.flavianoriko.gestao_vagas.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    /* indicar q é um objeto ja gerenciado pelo spring e a gnt esta sobrescrevendo */
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
     http.csrf(csrf-> csrf.disable())
     .authorizeHttpRequests(auth-> {auth.requestMatchers("/candidate/").permitAll()
     .requestMatchers("/company/").permitAll()
     .requestMatchers("/auth/company").permitAll(); /* tipo de ataque cibernetico. Ele vai desabilitar pra gnt poder configurar como quiser */ 
     auth.anyRequest().authenticated();
     });
     
     return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
