package com.prog_utente.davide.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "utenti")
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column (unique=true, nullable=false)
    @NotBlank (message="Il nome è obbligatorio") // Validazione per assicurarsi che il campo non sia vuoto e non contenga solo spazi bianchi
    @Size(min = 3, max = 50, message = "L'username deve essere tra 3 e 50 caratteri")
    private String username;

    @Column
    @NotBlank (message="Il telefono è obbligatorio")
    private String telefono;

    @Column(unique = true, nullable = false)
    @NotBlank (message="L'email è obbligatoria")
    @Email (message="Il formato dell'email non è valido")  // Validazione in un formato corretto di email
    private String email;

    @Column
    @NotNull (message="La data di nascita è obbligatoria")
    //@NotBlank funziona con le stringhe
    private LocalDate dataNascita;

    @Column(nullable = false)
    @NotBlank (message="La password è obbligatoria")
    //@Min VIENE UTILIZZATO PER INT
    @Size(min=8, message="La password deve avere almeno 8 caratteri") // Validazione per assicurarsi che la password abbia almeno 8 caratteri
    private String password;

    @Column
    @NotNull (message="Il ruolo è obbligatorio")
    //@NotBlank NON SI PUO' UTILIZZARE CON ENUM
    @Enumerated (EnumType.STRING) //(EVITIAMO DI METTERE @ENTITY IN RUOLO) Salva l'enum come stringa nel database
    private Ruolo ruolo;
}