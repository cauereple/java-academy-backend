package com.corso.fcSpring.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


// passo 1
@Entity
@Table(name="utenti")
public class Utenti {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID") //le colonne le metto in maiuscolo
	private int id;
	
	@Column(name="NOME")
	private String nome;
	
	@Column(name="COGNOME")
	private String cognome;
	
	@Column(name="INDIRIZZO")
	private String indirizzo;
	
	@Column(name="CAP")
	private String cap;
	
	@Column(name="CITTA")
	private String citta;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="PASSWORD")
	private String password;
	
	@OneToMany(mappedBy="utente")  // Nel mappedBy specifico l'attributo nell'altra classe della chiave esterna in Post
	private List<Post> posts;  // = new ArrayList<Post>
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getCitta() {
		return citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	
	
}
