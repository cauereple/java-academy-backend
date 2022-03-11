package com.corso.fcSpring.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corso.fcSpring.entity.Utenti;
import com.corso.fcSpring.repository.UtentiRepository;

// passo 3

@Service
public class UtentiService {
	// usiamo la dependency injection per dire di usare UtentiRepository quando gli
	// serve

	@Autowired
	private UtentiRepository ur; // crea un oggetto di tipo UtentiRepository , l'interfaccia viene gestita come
									// se fosse una classe

	// mostra tutti gli utenti
	public Iterable<Utenti> utentiAll() {
		return ur.findAll(); // la funzione findAll è dentro JPA
	}

	// mostra un utente con id specifico
	public Utenti utente(int id) throws Exception {

		if (!ur.existsById(id)) {
			String msg = "KO - id utente inesistente";
			throw new Exception(msg);
		} else {
			return ur.findById(id).get(); // ritorna un oggetto di tipo Utente
		}
	}

	// mostra un utente con email specifica
	public Utenti utenteEmail(String email) throws Exception {

		if (!ur.existsByEmailIgnoreCase(email)) {
			String msg = "KO - nessun utente ha questa email";
			throw new Exception(msg);
		} else {
			return ur.findByEmail(email).get(); // ritorna un oggetto di tipo Utente
		}
	}

	// inseriamo nel database un nuovo utente
	@Transactional
	public String utentiNew(Utenti u) throws Exception { // gli passiamo all'interno un oggetto di tipo Utenti

		String msg = "OK - Inserimento effettuato";
		if (ur.existsByEmailIgnoreCase(u.getEmail())) { // controllo se l'email esiste gia nel mio database
			msg = "KO - Indirizzo email esistente. Inserimento non effettuato!";

			throw new Exception(msg); // blocca l'esecuzione del codice e notifica l'errore a livello superiore

		}
		ur.save(u);

		return msg;
	}

	// modifica di un elemento nel database
	@Transactional
	public String utentiUpdate(Utenti datiUtente) throws Exception {

		String msg = "OK - Modifica effettuata";

		// if (ur.findById(idUtenteOld) == null) {
		if (!ur.existsById(datiUtente.getId())) {
			msg = "KO - Id inesistente";
			throw new Exception(msg);
		}
		// else if (ur.existsByEmailIgnoreCase(datiUtente.getEmail())) {
		// deve controllare se una email con id diverso da quello ricevuto
		else if ((ur.selUtenteByEmail(datiUtente.getEmail(), datiUtente.getId())).size() > 0) {
			msg = "KO - email gia esistente";
			throw new Exception(msg);
		} else {
			datiUtente.setId(datiUtente.getId()); // setto l'id uguale a quello di prima in caso qualcuno lo avesse
													// cambiato
			// datiUtente.setId(idUtenteOld);
			ur.save(datiUtente);
			return msg;
		}

	}

	@Transactional
	public String utentiDelete(int id) throws Exception {
		String msg = "OK - Cancellazione effettuata";

		// Optional<Utenti> lista =ur.findById(id);
		// Utenti u=ur.findById(id).get();
		// if (u==null) {
		if (!ur.existsById(id)) {
			msg = "KO - id utente inesistente";
			throw new Exception(msg);
		} else {
			Utenti u = ur.findById(id).get();
			ur.delete(u);
			return msg;
		}

	}

	// restituiscec true quando esiste una email per un id diverso da quello
	// ricevuto
	@Transactional
	public boolean checkEmail(int id, String email) {
		if ((ur.selUtenteByEmail(email, id)).size() > 0)
			return false;
		else
			return true;
	}
	
	// restituiscec true quando esiste una email e una password associati
	// ricevuto
	@Transactional
	public boolean checkEmailAndPass(String password, String email) {
		if ((ur.selUtenteByEmailAndPass(email, password)).size() > 0)
			return true;
		else
			return false;
	}
	
	

}
