package com.corso.fcSpring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;






@Entity
@Table(name="Post")
public class Post {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID") //le colonne le metto in maiuscolo
	private int id;
	
	@Column(name="TESTO")
	private String testo;
	
	@ManyToOne //1 utente può avere più post
	@JoinColumn(name="ID_UTENTE") //per gestire la chiave esterna
	private Utenti utente;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}

	@JsonIgnore //evita la ricorsione
	public Utenti getUtente() {
		return utente;
	}

	
	public void setUtente(Utenti utente) {
		
		this.utente=utente;
		//this.utente=ur.getById(id);
	}
	
	
	
	
	
}
