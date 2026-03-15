package com.prog_utente.dto;

import com.prog_utente.model.Ruolo;

import lombok.Data;

@Data // Lombok annotation per generare automaticamente getter, setter, toString, equals e hashCode
public class UtenteDTO {

    private String username;
    private String telefono;
    private String email;
    private String dataNascita;
    private Ruolo ruolo;
    
}
