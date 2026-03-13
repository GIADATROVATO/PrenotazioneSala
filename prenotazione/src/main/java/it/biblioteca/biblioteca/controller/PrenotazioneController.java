package it.biblioteca.biblioteca.controller;

import java.time.LocalDate;
import java.time.Month;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.biblioteca.biblioteca.entity.Prenotazione;
import it.biblioteca.biblioteca.service.PrenotazioneService;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {
	private PrenotazioneService prenotazioneSev;

	public PrenotazioneController(PrenotazioneService prenotazioneSev){
		this.prenotazioneSev= prenotazioneSev;
	}
	//POST —> crea prenotazione
	@PostMapping("/prenotazione")
	public Prenotazione creaPrenotazione(
				@RequestParam Long utenteId, 
				@RequestParam Long salaId, 
				@RequestParam LocalDate inizio){
		return prenotazioneSev.creaPrenotazione( utenteId, salaId, inizio);
	}
	//PUT —> chiudo prenotazione 
	@PutMapping("/{id}/chiudi")
	public Prenotazione chiudiPrenotazione(@PathVariable Long id, @RequestParam LocalDate dataFine){
		return prenotazioneSev.chiudiPrenotazione(id, dataFine);
	}
	@GetMapping("/statistiche")
	public Map<String,Object> statistiche(){
		return prenotazioneSev.statistichePrenotazioni();
	/*
	 * 		GET /prenotazioni/statistiche
	 * 		{
	 * 			"Numero totale di prenotazioni": 25,
	 * 			"Prenotazioni attive": 3,
	 * 			"Durata media giorni": 3.2
	 * 		}		
	 */
	}
	@GetMapping("/statistiche/top-3")
	public Map<String,Long> topSale(){
		return prenotazioneSev.topTreSale();
	}
	@GetMapping("/statistiche/mensili")
	public Map<Month,Long> prenotazioniMensili(){
		return prenotazioneSev.prenotazioniPerMese();
	}
}
