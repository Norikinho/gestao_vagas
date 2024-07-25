package br.com.flavianoriko.gestao_vagas.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfig {
    /* indicar q é um objeto ja gerenciado pelo spring e a gnt esta sobrescrevendo */
    @Autowired
    private SecurityFilter securityFilter;
    
    @Autowired
    private SecurityCandidateFilter securityCandidateFilter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
     http.csrf(csrf-> csrf.disable())
     .authorizeHttpRequests(auth-> {auth
     .requestMatchers("/candidate/").permitAll()
     .requestMatchers("/company/").permitAll()
     .requestMatchers("/candidate/auth").permitAll()
     .requestMatchers("/auth/company").permitAll(); /* tipo de ataque cibernetico. Ele vai desabilitar pra gnt poder configurar como quiser */ 
     auth.anyRequest().authenticated();
     })
     .addFilterBefore(securityFilter, BasicAuthenticationFilter.class)
     .addFilterBefore(securityCandidateFilter, BasicAuthenticationFilter.class);
     
     return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
