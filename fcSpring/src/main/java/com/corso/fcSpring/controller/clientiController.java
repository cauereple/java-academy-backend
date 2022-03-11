package com.corso.fcSpring.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.corso.fcSpring.entity.Utenti;
import com.corso.fcSpring.service.UtentiService;

//passo 4

@RestController
@RequestMapping("/utenti")
@CrossOrigin(origins="*")
public class clientiController {

	@Autowired
	private UtentiService us;

	// segue gli endpoint del controller clienti

	@GetMapping("/test")
	public String testSpring() {
		return "\"Sono il progetto che ti ritorna questa stringa\""; // quindi se scrivo sul browser
																		// localhost:8050/clienti/test mi ritorna questa
																		// stringa
	} // stringa racchiusa fra "\"__"\" diventa un json

	@GetMapping(value = "/all", produces = "application/json")
	public ResponseEntity<List<Utenti>> tuttiGliUtenti() {

		List<Utenti> dati = (List<Utenti>) us.utentiAll();
		return new ResponseEntity<List<Utenti>>(dati, HttpStatus.OK);
	}
	
	@GetMapping(value = "/id/{id}", produces = "application/json")
	public ResponseEntity<?> utente(@PathVariable("id") int id) {

		Utenti u;
		try {
			u = us.utente(id);
			return new ResponseEntity<Utenti>(u, HttpStatus.OK);
		} catch (Exception e) {
			
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
		
	}
	
	@GetMapping(value = "/email/{email}", produces = "application/json")
	public ResponseEntity<?> utente(@PathVariable("email") String email) {

		Utenti u;
		try {
			u= us.utenteEmail(email);
			return new ResponseEntity<Utenti>(u, HttpStatus.OK);
		} catch (Exception e) {
			
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
		
	}
	
	//validazione email lato server
	@GetMapping(value = "id/{id}/email/{email}", produces = "application/json")
	public ResponseEntity<?> utente(@PathVariable("email") String email,@PathVariable("id") int id) {

		
		try {
			boolean b=us.checkEmail(id, email);
			return new ResponseEntity<Boolean>(b, HttpStatus.OK);
		} catch (Exception e) {
			
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
		
	}
	

	// inserimento
	@PostMapping(path = "/new", consumes = { MediaType.ALL_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> utentiNew(@RequestBody Utenti u) { // ritorna una ResponseEntity perchè vogliamo
																		// ritornare una stringa ed uno stato
		try {
			String msg = us.utentiNew(u);
			// ArrayList<String> lista= new ArrayList<>();
			// lista.add(msg);
			return new ResponseEntity<String>(msg, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT); // e.getMessage() ritorna il msg che
																					// throws la Exception nel service
		}

	}

	// modifica
	//@PutMapping(path = "/edit/{idu}")
	//public ResponseEntity<String> utentiUpdate(@PathVariable("idu") int idUtenteDaModificare, @RequestBody Utenti utenteNuoviDati) {
		@PutMapping(path = "/edit", consumes = { MediaType.ALL_VALUE, MediaType.APPLICATION_JSON_VALUE })
		public ResponseEntity<String> utentiUpdate(@RequestBody Utenti utenteNuoviDati) {
		try {
			String msg = us.utentiUpdate(utenteNuoviDati);
			return new ResponseEntity<String>(msg, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}

	}
	
		
		// cancellazione utente
		@DeleteMapping(path="/delete/{idu}")
		public ResponseEntity<String> utentiDelete(@PathVariable("idu") int id){
			
			try {
				String msg=us.utentiDelete(id);
				return new ResponseEntity<String>(msg, HttpStatus.CREATED);
			} catch (Exception e) {
				return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
			}
		}
		
	
		//
		@GetMapping(value = "/email/{email}/password/{password}", produces = "application/json")
		public ResponseEntity<?> utente(@PathVariable("email") String email,@PathVariable("password") String password) {

			
			try {
				boolean b=us.checkEmailAndPass(password,email);
				return new ResponseEntity<Boolean>(b, HttpStatus.OK);
			} catch (Exception e) {
				
				return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
			}
			
		}
		
		

}
