package it.biblioteca.biblioteca.service;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import it.biblioteca.biblioteca.entity.Prenotazione;
import it.biblioteca.biblioteca.entity.Sala;
import it.biblioteca.biblioteca.entity.Utente;
import it.biblioteca.biblioteca.repository.PrenotazioneRepository;
import it.biblioteca.biblioteca.repository.SalaRepository;
import it.biblioteca.biblioteca.repository.UtenteRepository;
import jakarta.transaction.Transactional;

@Service
public class PrenotazioneService {
	private SalaRepository salaRepo;
	private PrenotazioneRepository prenotazioneRepo;
	private UtenteRepository utenteRepo;
	public PrenotazioneService(UtenteRepository utenteRepo, SalaRepository salaRepo, PrenotazioneRepository prenotazioneRepo){

		this.utenteRepo = utenteRepo;
		this.salaRepo = salaRepo;
		this.prenotazioneRepo = prenotazioneRepo;
		}
	@Transactional
	public Prenotazione creaPrenotazione(Long utenteId,Long salaId, LocalDate inizio) {
		Utente u= utenteRepo.findById(utenteId).orElseThrow(()-> new RuntimeException("utente non trovato"));
		Sala s= salaRepo.findById(salaId).orElseThrow(()-> new RuntimeException("sala non trovata"));
		
		if(!s.isDisponibile()) { throw new RuntimeException("sala non disponibile");}
			Prenotazione p= new Prenotazione();
			p.setId(salaId);
			p.setSala(s);
			p.setDataInizio(inizio);
			p.setDataFine(null);
			
			s.setDisponibile(false);
			salaRepo.save(s);
			return prenotazioneRepo.save(p);
	}
	@Transactional
	public Prenotazione chiudiPrenotazione(Long idPrenotazione, LocalDate fine) {
		Prenotazione p= prenotazioneRepo.findById(idPrenotazione).orElseThrow(()->new RuntimeException("prenotazione non trovata"));

		if( p.getDataFine()!=null){ throw new RuntimeException("Prenotazione già chiusa");}
	
			p.setDataFine(fine);
			Sala s= p.getSala();
			s.setDisponibile(true);
			salaRepo.save(s);
/* ----> */	logPrenotazioneChiusa(p);	
			return prenotazioneRepo.save(p);
	}
	public Map<String,Object> statistichePrenotazioni(){
		List<Prenotazione> prenotazioni= prenotazioneRepo.findAll();
		int totali= prenotazioni.size();
		long attive= prenotazioni.stream().filter(p->p.getDataFine()==null).count();
		double durataMedia= prenotazioni.stream().filter(p->p.getDataFine()!= null).
				mapToLong(p-> ChronoUnit.DAYS.between(p.getDataInizio(), p.getDataFine())).average().orElse(0);
		Map<String,Object> stats= new HashMap<>();
		stats.put("Numero totale di prenotazioni", totali);
		stats.put("Prenotazioni attive", attive);
		stats.put("Durata media giorni", durataMedia);
		return stats;
	}
	
	public Map<String,Long> topTreSale(){
		List<Prenotazione> prenotazioni=prenotazioneRepo.findAll();
		
		return prenotazioni.stream()
				.collect(Collectors.groupingBy( p->p.getSala().getNome(), Collectors.counting()
				))
				.entrySet().stream().sorted(Map.Entry.<String,Long>comparingByValue().reversed()).limit(3)
				.collect(Collectors.toMap(
						Map.Entry::getKey, 
						Map.Entry::getValue
				));
	}
	
	public Map<Month,Long> prenotazioniPerMese(){
		List<Prenotazione> prenotazioni=prenotazioneRepo.findAll();
		
		return prenotazioni.stream().collect(Collectors.groupingBy(
				p->p.getDataInizio().getMonth(), Collectors.counting()));
	}
	
	public Prenotazione prenotazionePiuLunga(){
		List<Prenotazione> prenotazioni=prenotazioneRepo.findAll();
		
		return prenotazioni.stream().filter(p->p.getDataFine()!=null)
				.max(Comparator.comparingLong(
						p-> ChronoUnit.DAYS.between(p.getDataInizio(),p.getDataFine())
				)).orElse(null);
		
	}
	
	public Map<String,Long> prenotazioniPerSala(){
		List<Prenotazione> prenotazioni=prenotazioneRepo.findAll();
		return prenotazioni.stream().collect(Collectors.groupingBy(p->p.getSala().getNome(), Collectors.counting()));
	}
	
	public Map.Entry<String,Long> salaPiuUsata() {
		return prenotazioneRepo.findAll().stream().collect(Collectors.groupingBy(
		p->p.getSala().getNome(), Collectors.counting())).entrySet().stream().max(Map.Entry.comparingByValue()).orElse(null);
	}
	public Map.Entry<String,Long> utenteConPiuPrenotazioni(){
		return prenotazioneRepo.findAll().stream().collect(Collectors.groupingBy(p->p.getUtente().getNome(),Collectors.counting()))
				.entrySet().stream().max(Map.Entry.comparingByKey()).orElse(null);
	}
	
	private void logPrenotazioneChiusa(Prenotazione p) {
		long durata= ChronoUnit.DAYS.between(p.getDataInizio(),p.getDataFine());
		System.out.println(
			"Prenotazione chiusa | id=" +p.getId()+ " | sala=" +p.getSala().getNome()+ " | durata=" +durata + "giorni");
	}
}
