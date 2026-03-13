package it.biblioteca.biblioteca.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import it.biblioteca.biblioteca.entity.Utente;
import it.biblioteca.biblioteca.repository.UtenteRepository;
@Service
public class UtenteService {
	private UtenteRepository utenteRepo;
	public UtenteService(UtenteRepository utenteRepo) {
		this.utenteRepo=utenteRepo;
	}
	public Utente creaUtente(String nome) {
		Utente u= new Utente();
		u.setNome(nome);
		return utenteRepo.save(u);
	}
	public List<Utente> getAllUtenti() {
	    return utenteRepo.findAll();
	}
	
	public Utente getUtenteById(Long id){
		Optional<Utente> u= utenteRepo.findById(id);
		return u.orElseThrow(()->new RuntimeException("Utente non trovato"));
	}
}
