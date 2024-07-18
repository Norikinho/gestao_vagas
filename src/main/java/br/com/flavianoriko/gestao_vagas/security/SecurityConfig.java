package br.com.flavianoriko.gestao_vagas.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    /* indicar q é um objeto ja gerenciado pelo spring e a gnt esta sobrescrevendo */
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
     http.csrf(csrf-> csrf.disable()); /* tipo de ataque cibernetico. Ele vai desabilitar pra gnt poder configurar como quiser */ 
        return http.build();
    }
}
