package com.prog_utente.davide.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration //QUESTA CLASSE E' UN COMPONENTE DI CONFIGURAZIONE BEAN
@EnableWebSecurity // SISTEMA DI SICUREZZA WEB, PRENDE IL CONTROLLO DI TUTTE LE RICHIESTE HTTP 
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() { //PASSWORDENCORDER E' UN BEAN CHE CI PERMETTE DI CRIPTARE E VERIFICARE LA PASSWORD
        return new BCryptPasswordEncoder(); //BCRYPTPASSWORDENCORDER IMPLEMENTAZIONE PER LA CRIPTAZIONE
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception { //SECURITYFILTERCHAIN CONFIGURAZIONE TEMPORANEO PER DISABILITARE L'AUTENTICAZIONE PER TESTARE 
        http
            .csrf(csrf -> csrf.disable()) // Disabilitiamo CSRF per semplificare (da abilitare in produzione)
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll() // Permettiamo tutte le richieste senza autenticazione
            );
        
        return http.build();
    }
}