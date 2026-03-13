package it.biblioteca.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.biblioteca.biblioteca.entity.Utente;

public interface UtenteRepository extends JpaRepository<Utente,Long>{

}
