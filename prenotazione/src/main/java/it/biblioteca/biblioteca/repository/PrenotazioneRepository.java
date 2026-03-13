package it.biblioteca.biblioteca.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.biblioteca.biblioteca.entity.Prenotazione;

public interface PrenotazioneRepository 
	extends JpaRepository<Prenotazione,Long> {
	List<Prenotazione> findByDataFineBefore(LocalDate data);
	/*
	 * Spring genera SELECT * FROM prenotazioni WHERE data_fine < data
	 * 
	 */
}
