package com.prog_utente.davide.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prog_utente.davide.model.Ruolo;
import com.prog_utente.davide.model.Utente;
import com.prog_utente.davide.service.UtenteService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController  //COMPONENTE CHE GESTISCE CHIAMATE HTTP (@CONTROLLER()COMPONENTE MVC DOVE CI SONO LE RICHIESTE HTTP + @RESPONSEBODY(DEVE ESSERE SCRITTO IN FORMATO JACKSON))
@RequestMapping ("/api/utenti")//PREFISSO COMUNE PER TUTTI
@RequiredArgsConstructor
public class UtenteController {

    private final UtenteService utenteService;

    @PostMapping
    public ResponseEntity<Utente> creaUtente(@Valid @RequestBody Utente utente) {
        log.info("POST /api/utenti - Creazione nuovo utente: {}", utente.getUsername());
        try {
            Utente nuovoUtente = utenteService.creaUtente(utente);
            log.info("Creazione andata a buon fine: {}");
            return new ResponseEntity<>(nuovoUtente, HttpStatus.CREATED);
        } catch (IllegalArgumentException e){
            log.error("Errore nella creazione utente: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<Utente>> getAllUtenti() {
        List<Utente> utenti = utenteService.getAllUtenti();
        log.info("GET /api/utenti - Recupero tutti gli utenti");
        return new ResponseEntity<>(utenti, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Utente> getUtenteById(@PathVariable Long id) {
        log.info("GET /api/utenti/{} - Recupero utente", id);
        try{
            Utente utente = utenteService.getUtenteById(id);
            log.info("Richiesta andata a buon fine: {}");
            return new ResponseEntity<>(utente, HttpStatus.OK);
        } catch (IllegalArgumentException e){
            log.error("Utente non trovato con ID: {}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("username/{username}")
    public ResponseEntity<Utente> getUtenteByUsername(@PathVariable String username) {
        log.info("GET /api/utenti/username/{} - Recupero utente", username);
        try{
            Utente utente = utenteService.getUtenteByUsername(username);
            log.info("Richiesta andata a buon fine: {}");
            return new ResponseEntity<>(utente, HttpStatus.OK);
        } catch (IllegalArgumentException e){
            log.error("Utente non trovato con username: {}", username);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Utente> aggiornaUtente(@PathVariable Long id, Utente utenteDetails) {
        try{
            Utente utente = utenteService.aggiornaUtente(id, utenteDetails);
            log.info("Aggiornamento andato a buon fine: {}");
            return new ResponseEntity<>(utente, HttpStatus.OK);
        } catch (IllegalAccessException e){
            log.error("Errore nell'aggiornamento utente: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Utente> eliminaUtente(@PathVariable Long id, Utente utenteDetails){
        try{
            utenteService.eliminaUtente(id, utenteDetails);
            log.info("Eliminazione andata a buon fine: {}");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e){
            log.error("Errore nell'eliminazione utente: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/ruolo/{ruolo}")
    public ResponseEntity<List<Utente>> getUtentiByRuolo(@PathVariable Ruolo ruolo) {
        List<Utente> utenti = utenteService.getUtentiByRuolo(ruolo);
        log.info("Richiesta andata a buon fine: {}");
        return new ResponseEntity<>(utenti, HttpStatus.OK);
    }
}
