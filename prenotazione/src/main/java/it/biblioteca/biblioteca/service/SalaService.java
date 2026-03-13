package it.biblioteca.biblioteca.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import it.biblioteca.biblioteca.entity.Sala;
import it.biblioteca.biblioteca.repository.SalaRepository;

@Service
@Transactional
public class SalaService {
	private SalaRepository salaRepo;
	public SalaService( SalaRepository salaRepo){
		this.salaRepo=salaRepo;
	}
	public List<Sala> getAllSale() {
		return salaRepo.findAll();
	}
	public Sala creaSala(String nome) {
		Sala s=new Sala();
		return salaRepo.save(s);
	}
	
	public Sala modificaPrenotazione(Long id,boolean disponibile) {
		Sala i= salaRepo.getById(id);
		i.setDisponibile(disponibile);
		return salaRepo.save(i);
	}
	
	public Optional<Sala> modificaDellaPrenotazione(Long id,boolean disponibile) {
		Optional<Sala> s= salaRepo.findById(id);
		if(s.isPresent()) {
			Sala i=s.get();
			i.setDisponibile(disponibile);
			return Optional.of(salaRepo.save(i));
		}
		return Optional.empty();
	}
	public Optional<Sala> modificaDellaPrenotazioneInCorso(Long id, boolean dip){
		return salaRepo.findById(id).map(sala -> { 
			sala.setDisponibile(dip);
			return salaRepo.save(sala);
			
		});
	}
	
	
		
	public Sala getSalaById(Long id) {	
		Optional<Sala> s=salaRepo.findById(id);
		return s.orElseThrow(()-> new RuntimeException("sala non trovata"));
	}
}
