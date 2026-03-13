package it.biblioteca.biblioteca.scheduler;

import java.time.LocalDate;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import it.biblioteca.biblioteca.entity.Prenotazione;
import it.biblioteca.biblioteca.repository.PrenotazioneRepository;

/*
 * 
 */

@Component
public class PrenotazioneScheduler {
	private final PrenotazioneRepository repository;
	public PrenotazioneScheduler(PrenotazioneRepository repository) {
		this.repository=repository;
	}
	@Scheduled( cron = "0 0 2 * * *")
	public void cancellaPrenotazioniVecchie() {
		LocalDate limit= LocalDate.now().minusDays(30);
		List<Prenotazione> vecchie= repository.findByDataFineBefore(limit);
		
		repository.deleteAll(vecchie);
		System.out.println("Vecchie prenotazioni cancellate: " +vecchie.size());
		
	}
	
}
