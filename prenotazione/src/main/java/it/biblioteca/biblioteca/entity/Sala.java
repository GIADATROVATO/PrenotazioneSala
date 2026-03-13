package it.biblioteca.biblioteca.entity;

import jakarta.persistence.*;

@Entity
public class Sala {
	@Id
	@GeneratedValue 
	private Long id;
	private String nome; 
	private boolean disponibile= true;
	public Sala(){}
	public Sala(String nome, boolean disponibile){
		this.nome=nome;
		this.disponibile=true;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public boolean isDisponibile() {
		return disponibile;
	}
	public void setDisponibile(boolean disponibile) {
		this.disponibile = disponibile;
	}
	
	
}
