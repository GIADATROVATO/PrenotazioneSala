package it.biblioteca.biblioteca.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import it.biblioteca.biblioteca.entity.Sala;
import it.biblioteca.biblioteca.service.SalaService;

@RestController
@RequestMapping("/sale")
public class SalaController {
	private final SalaService salaService;
	public SalaController(SalaService salaService) {
		this.salaService=salaService;
	}
	@GetMapping
	public List<Sala> getAll(){
		return salaService.getAllSale();
	}
	//http:localhost:8080/sale/5
	@GetMapping("/{id}")
	public Sala getSala(@PathVariable Long id) {
		return salaService.getSalaById(id);
	}
	
	//http://localhost:8081/sale?id=SalaStudio
	@PostMapping
	public Sala salva(@RequestParam String nome) {
		return salaService.creaSala(nome);
	}
	
	//http://localhost:8081/sale/5/disponibile?dip=true
	@PutMapping("/{id}/disponibile")
	public Sala cambiaDisponibilita(@PathVariable Long id,@RequestParam boolean dip) {
		return salaService.modificaPrenotazione(id, dip);
	}
}
