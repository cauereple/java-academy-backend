package com.corso.fcSpring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.corso.fcSpring.entity.Post;
import com.corso.fcSpring.entity.Utenti;
import com.corso.fcSpring.service.PostService;


@RestController
@RequestMapping("/post")
@CrossOrigin(origins="*")
public class postController {
	
	@Autowired
	private PostService ps;
	
	@GetMapping(value = "/all", produces = "application/json")
	public ResponseEntity<List<Post>> tuttiIPost() {

		List<Post> dati = (List<Post>) ps.postAll();
		return new ResponseEntity<List<Post>>(dati, HttpStatus.OK);
	}
	
	@GetMapping(value = "/id/{id}", produces = "application/json")
	public ResponseEntity<?> post(@PathVariable("id") int id) {

		Post p;
		try {
			p = ps.post(id);
			return new ResponseEntity<Post>(p, HttpStatus.OK);
		} catch (Exception e) {
			
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
		
	}
	
	// restituisce i post di un utente
	@GetMapping(value = "/idutente/{id}", produces = "application/json")
	public ResponseEntity<?> postByUtente(@PathVariable("id") int id) {

		
		try {
			List<Post> listaPost= ps.postsById(id);
			return new ResponseEntity<List<Post>>(listaPost, HttpStatus.OK);
		} catch (Exception e) {
			
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
		
	}
	
	// inserimento
	@PostMapping(path = "/new/{idu}", consumes = { MediaType.ALL_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> postNew(@PathVariable("idu") int id,@RequestBody String testo) { // ritorna una ResponseEntity perchè vogliamo
																// ritornare una stringa ed uno stato
		try {
			
			String msg = ps.postNew(id,testo);
			// ArrayList<String> lista= new ArrayList<>();
			// lista.add(msg);
			return new ResponseEntity<String>(msg, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT); // e.getMessage() ritorna il msg che
																					// throws la Exception nel service
		}

	}
	
	//modifica testo post
	@PutMapping(path="/edit1/{idp}", consumes = { MediaType.ALL_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> editPost(@PathVariable("idp") int id,@RequestBody String testo) { 
		
		try {
			String msg=ps.postUpdate(id,testo);
			return new ResponseEntity<String>(msg, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}

	}
	
	 //Modifica
    @PutMapping(path="/edit")
    public ResponseEntity<String> postUpdate(@RequestBody Post postNuoviDati){

        try {
            String msg = ps.postUpdate2(postNuoviDati);
            return new ResponseEntity<String>(msg, HttpStatus.CREATED);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
        }

    }
	
	
	
	

}
