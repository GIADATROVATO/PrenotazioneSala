package it.biblioteca.biblioteca.entity;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
public class Prenotazione {
	@Id
	@GeneratedValue 
	private Long id;

	@ManyToOne
	private Utente u;

	@ManyToOne
	private Sala s;

	private LocalDate dataInizio;
	private LocalDate dataFine;
	public Prenotazione(){}
	public Prenotazione(Utente u, Sala s,LocalDate dataInizio, LocalDate dataFine ){
		this.u = u;
       	this.s = s;
        this.dataInizio = dataInizio;
       	this.dataFine = null;
   	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Utente getUtente() {
		return u;
	}
	public void setUtente(Utente u) {
		this.u = u;
	}
	public Sala getSala() {
		return s;
	}
	public void setSala(Sala s) {
		this.s = s;
	}
	public LocalDate getDataInizio() {
		return dataInizio;
	}
	public void setDataInizio(LocalDate dataInizio) {
		this.dataInizio = dataInizio;
	}
	public LocalDate getDataFine() {
		return dataFine;
	}
	public void setDataFine(LocalDate dataFine) {
		this.dataFine = dataFine;
	}
	
	
}
