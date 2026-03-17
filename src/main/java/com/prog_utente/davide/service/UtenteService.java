package com.prog_utente.davide.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prog_utente.davide.model.Ruolo;
import com.prog_utente.davide.model.Utente;
import com.prog_utente.davide.repository.UtenteRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service //REGISTRA QUESTA CLASSE SERVICE, FALLA DIVENTARE INNIETABILE (@AUTOWIRED, IL NEW)
public class UtenteService {
    private final UtenteRepository utenteRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Utente creaUtente(Utente utente) {
        log.info("Tentativo di creazione utente: {}", utente.getUsername());

        if (utenteRepository.existsByUsername(utente.getUsername())) {
            log.warn("Username già esistente: {}", utente.getUsername());
            throw new IllegalArgumentException("Username già esistente");
        }
        if (utenteRepository.existsByEmail(utente.getEmail())) {
            log.warn("Email già esistente: {}", utente.getEmail());
            throw new IllegalArgumentException("Email già esistente");
        }
        //SE RIESCE A PASSARE UN NULL MA DAL MODEL NON POTREBBE CON NOTNULL
        if(utente.getRuolo() == null){
            utente.setRuolo(Ruolo.USER); // Imposta il ruolo predefinito se nullo
        }

        String passwordCriptata = passwordEncoder.encode(utente.getPassword());
        utente.setPassword(passwordCriptata);
        log.debug("Password criptata per utente {}: {}", utente.getUsername(), passwordCriptata);

        Utente utenteSalvato = utenteRepository.save(utente);
        log.info("Utente creato con successo. ID: {}", utenteSalvato.getId());
        return utenteSalvato;
    }

    @Transactional
    public List<Utente> getAllUtenti(){
        log.debug("Recupero tutti gli utenti");
        return utenteRepository.findAll();
    }

    @Transactional
    public Utente getUtenteById(Long id) {
        log.debug("Recupero utente con ID: {}", id);
        return utenteRepository.findById(id).orElseThrow(()-> new IllegalAccessError("Utente non trovato"));
    }

    @Transactional
    public Utente getUtenteByUsername(String username){
        log.debug("Recupero utente con username: {}", username);
        return utenteRepository.findByUsername(username).orElseThrow(()-> new IllegalAccessError("Utente non trovato"));
    }

    @Transactional
    public Utente aggiornaUtente (Long id, Utente utenteDetails) throws IllegalAccessException{
        log.info("Aggiornamento utente con ID: {}", id);

        Utente utenteEsistente = utenteRepository.findById(id).orElseThrow(()-> new IllegalAccessError("Utente non trovato"));
        if(!utenteEsistente.getEmail().equals(utenteDetails.getEmail()) || !utenteEsistente.getUsername().equals(utenteDetails.getUsername())){
            throw new IllegalAccessException("Credenziali errate!");
        }

        if (!passwordEncoder.matches(utenteDetails.getPassword(), utenteEsistente.getPassword())) {
            log.warn("Tentativo di aggiornamento con password errata per utente ID: {}", id);
            throw new IllegalAccessException("Password errata!");
        }

        utenteEsistente.setUsername(utenteDetails.getUsername());
        utenteEsistente.setEmail(utenteDetails.getEmail());
        utenteEsistente.setDataNascita(utenteDetails.getDataNascita());
        utenteEsistente.setTelefono(utenteDetails.getTelefono());

        log.info("Utente aggiornato con successo. ID: {}", id);
        return utenteRepository.save(utenteEsistente);
    }

    @Transactional
    public void eliminaUtente (Long id, Utente utenteDetails){
        log.info("Eliminazione utente con ID: {}", id);
        Utente utenteEsistente = utenteRepository.findById(id).orElseThrow(()-> new IllegalAccessError("Utente non trovato"));

        if (!passwordEncoder.matches(utenteDetails.getPassword(), utenteEsistente.getPassword())) {
            log.warn("Tentativo di eliminazione con password errata per utente ID: {}", id);
            throw new IllegalArgumentException("Password errata!");
        }

        if (!utenteEsistente.getEmail().equals(utenteDetails.getEmail()) || !utenteEsistente.getUsername().equals(utenteDetails.getUsername())){
            throw new IllegalArgumentException("Utente non autorizzato");
        }
        utenteRepository.delete(utenteEsistente);
        log.info("Utente eliminato con successo. ID: {}", id);
    }

    @Transactional(readOnly = true) //READONLY SIGNIFICA CHE SALTA I CONTROLLI PERCHE' NON SI MODIFICANO DATI
    public List<Utente> getUtentiByRuolo(Ruolo ruolo){
    log.debug("Recupero utenti con ruolo: {}", ruolo);
    return utenteRepository.findByRuolo(ruolo); 
}
}