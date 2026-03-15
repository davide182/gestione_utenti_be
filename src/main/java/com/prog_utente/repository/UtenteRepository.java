package com.prog_utente.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prog_utente.model.Ruolo;
import com.prog_utente.model.Utente;

@Repository //REGISTRA QUESTA CLASSE REPOSITORY
//JPAREPOSITORY CHE E' IMPLEMENTATO GENERA SAVE(), FINDBYID() ECC...
//SPRING DATA IMPLEMENTA DA SOLO IL RESTO 
public interface  UtenteRepository extends JpaRepository<Utente, Long> {
    Optional<Utente> findByUsername(String username);

    Optional<Utente> findByEmail(String email);

    List<Utente> findByRuolo(Ruolo ruolo);//NON OPTIONAL PERCHE' NON CONTIENE UN SOLO OGGETTO

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);


}
