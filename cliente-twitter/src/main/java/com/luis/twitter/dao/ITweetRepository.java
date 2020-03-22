package com.luis.twitter.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.luis.twitter.model.Tweet;

@Repository
public interface ITweetRepository extends JpaRepository<Tweet, Integer> {
	public List<Tweet> findByUsuario(String usuario);
	
	public List<Tweet> findByUsuarioAndValidadoTrue(String usuario);
	
	@Transactional
	@Modifying
	@Query("update Tweet t set t.validado = TRUE where id = ?1")
	public int validaTweet(Long id);
}
