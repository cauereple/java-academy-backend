package com.corso.fcSpring.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corso.fcSpring.entity.Post;
import com.corso.fcSpring.entity.Utenti;
import com.corso.fcSpring.repository.PostRepository;
import com.corso.fcSpring.repository.UtentiRepository;

@Service
public class PostService {

	@Autowired
	private PostRepository pr;

	@Autowired
	private UtentiRepository ur;

	// mostra tutti i post
	public Iterable<Post> postAll() {
		return pr.findAll(); // la funzione findAll è dentro JPA
	}

	// mostra un post con id specifico
	public Post post(int id) throws Exception {

		if (!pr.existsById(id)) {
			String msg = "KO - id post inesistente";
			throw new Exception(msg);
		} else {
			return pr.findById(id).get(); // ritorna un oggetto di tipo post
		}
	}

	// mostra un post di un utente specifico
	public List<Post> postsById(int id) throws Exception {
		Optional<Utenti> u = ur.findById(id);
		if (!u.isPresent()) {
			throw new Exception("utente non trovato");
		}
		return pr.findByUtente(u.get());
	}

	// inseriamo nel database un nuovo post
	@Transactional
	public String postNew(int id, String newOne) throws Exception { // gli passiamo all'interno un oggetto di tipo
																	// Utenti

		Optional<Utenti> u = ur.findById(id);

		String msg = "OK - Inserimento effettuato";

		if (!u.isPresent()) {
			throw new Exception("utente non trovato");
		}
		Post p = new Post();
		p.setUtente(u.get());
		p.setTesto(newOne);
		pr.save(p);

		return msg;
	}

	// modifica di un elemento nel database
	@Transactional
	public String postUpdate(int idP, String testo) throws Exception {

		String msg = "OK - Modifica effettuata";
		Optional<Post> p = pr.findById(idP);

		// if (ur.findById(idUtenteOld) == null) {
		if (!p.isPresent()) {
			msg = "KO - Id inesistente";
			throw new Exception(msg);
		} else {
			// setto l'id uguale a quello di prima in caso qualcuno lo avesse cambiato
			// datiUtente.setId(idUtenteOld);
			p.get().setTesto(testo);
			pr.save(p.get());

			return msg;
		}
	}
	@Transactional
	 public String postUpdate2(Post datiPost) throws Exception {
	        String msg="Ok - modifica effettuata";
	        if(!pr.existsById(datiPost.getId())) {
	            msg="KO - Post inesistente!";
	            throw new Exception(msg);

	        }else {
	            Post p= new Post();
	            p=pr.getById(datiPost.getId());
	            p.setTesto(datiPost.getTesto());
	            pr.save(p);
	            return msg;
	        }
	    }

	@Transactional
	public String postDelete(int id) throws Exception {
		String msg = "OK - Cancellazione effettuata";

		// Optional<Utenti> lista =ur.findById(id);
		// Utenti u=ur.findById(id).get();
		// if (u==null) {
		if (!pr.existsById(id)) {
			msg = "KO - id post inesistente";
			throw new Exception(msg);
		} else {
			Post p = pr.findById(id).get();
			pr.delete(p);
			return msg;
		}

	}
}
