package com.corso.fcSpring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.corso.fcSpring.entity.Post;
import com.corso.fcSpring.entity.Utenti;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
	
	List<Post> findByUtente(Utenti u);
	
	Optional<Post> findById(int id);
	
	boolean existsById(int id);

}
