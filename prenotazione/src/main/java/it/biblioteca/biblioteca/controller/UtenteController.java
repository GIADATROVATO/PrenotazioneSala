package it.biblioteca.biblioteca.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.biblioteca.biblioteca.entity.Utente;
import it.biblioteca.biblioteca.service.UtenteService;

@RestController
@RequestMapping("/utenti")
public class UtenteController {
	private final UtenteService sev; 
	public UtenteController(UtenteService sev) {
		this.sev=sev;
	}
	//POST -> creare utente
	//POST http://localhost:8080/utenti?nome=Luca
	@PostMapping
	public Utente crea(@RequestParam String nome) {
		return sev.creaUtente(nome);
	}
	//GET -> tutti gli utenti
	//GET http://localhost:8080/utenti
	@GetMapping
	public List<Utente> getAll(){
		return sev.getAllUtenti();
	}
	//GET -> utente per id
	//GET http://localhost:8080/utenti/5
	@GetMapping("/{id}")
	public Utente getUtenteId(@PathVariable Long id) {
		return sev.getUtenteById(id);
	}
	

}
