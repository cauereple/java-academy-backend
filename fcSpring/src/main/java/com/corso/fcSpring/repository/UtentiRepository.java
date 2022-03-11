package com.corso.fcSpring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.corso.fcSpring.entity.Utenti;
/*
 * passo 2
 * una volta creato la entity si crea l'interfaccia del repository
 * estendendo la superInterfaccia JpaRepository che mette a disposizione ( subito disponibili 
 * senza bisogno di scrivere codice) i metodi di findAll, inserimento ( save), cancellazione(delete), modifica 
 * (save - ma all'interno del json ci sarà id utente)
 * 
 * se ho bisogno di metodi su un campo particolare Jpa mi mette a disposizione i metodi chiamati: findByNomecampo 
 * 
 * esempio: findByEmail
 */
@Repository
public interface UtentiRepository extends JpaRepository<Utenti, Integer> {
	
	
	@Query(value="select * from utenti where email= :emailricevuta and id != :idricevuto", nativeQuery=true)
	List<Utenti> selUtenteByEmail(@Param("emailricevuta") String email, @Param("idricevuto") int id);
	
	@Query(value="select * from utenti where email= :emailRicevuta and password = :passwordRicevuta", nativeQuery=true)
	List<Utenti> selUtenteByEmailAndPass(@Param("emailRicevuta") String email, @Param("passwordRicevuta") String password);


	
	
	Optional<Utenti> findByEmail(String email);
	
	// restituisce true/false a seconda del risultato della query select
	boolean existsByEmailIgnoreCase(String email);  // questo metodo esiste gia nell'interfaccia JPA
	
	// Optional indica che potrebbe non esistere un oggetto Utenti con quell'Id
	Optional<Utenti> findById(int id); // Optional ritorna null se non trova l'oggetto, altrimenti ritorna una lista di Utenti che hanno quell'id
	
	boolean existsById(int id);
	
}
