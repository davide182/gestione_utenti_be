package com.prog_utente.davide.config;

//import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

//import com.prog_utente.davide.model.Ruolo;
//import com.prog_utente.davide.model.Utente;
import com.prog_utente.davide.repository.UtenteRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component //REGISTRA QUESTA CLASSE COME BEAN DI SPRING 
//@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner { //COMMANDLINERUNNER ESEGUE IL CODICE CON L'AVVIO DELL'APP

    //private final UtenteRepository utenteRepository;
    //private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        log.info(" DataLoader disabilitato - Liquibase gestisce i dati iniziali");
    }
}
/*   @Override
    public void run(String... args) throws Exception { //STRING...ARGS E' USATO PER PASSARE UN NUMERO DI VARIABILI NEGLI ARGOMENTI
        log.info("Avvio DataLoader - Caricamento dati iniziali...");

        // Verifichiamo se ci sono già dati nel database
        if (utenteRepository.count() == 0) {
            log.info(" Database vuoto - Inserimento dati di esempio...");

            // Creazione utenti con il Builder
            Utente admin = Utente.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin123"))
                    .email("admin@example.com")
                    .telefono("1234567890")
                    .dataNascita(LocalDate.of(1990, 1, 1))
                    .ruolo(Ruolo.ADMIN)
                    .build();

            Utente user1 = Utente.builder()
                    .username("mario.rossi")
                    .password(passwordEncoder.encode("password123"))
                    .email("mario.rossi@example.com")
                    .telefono("0987654321")
                    .dataNascita(LocalDate.of(1995, 5, 15))
                    .ruolo(Ruolo.USER)
                    .build();

            Utente user2 = Utente.builder()
                    .username("luca.bianchi")
                    .password(passwordEncoder.encode("password456"))
                    .email("luca.bianchi@example.com")
                    .telefono("1122334455")
                    .dataNascita(LocalDate.of(1988, 10, 20))
                    .ruolo(Ruolo.USER)
                    .build();

            utenteRepository.save(admin);
            utenteRepository.save(user1);
            utenteRepository.save(user2);

            log.info(" Inseriti {} utenti di esempio", utenteRepository.count());
            
            log.debug("Admin: {}", admin.getUsername());
            log.debug("User1: {}", user1.getUsername());
            log.debug("User2: {}", user2.getUsername());

        } else {
            log.info("Database già popolato con {} utenti - Nessun inserimento eseguito", utenteRepository.count());
        }

        log.info(" DataLoader completato!");
    }
}
    */ 