package com.luis.twitter.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luis.twitter.dao.TweetDAO;
import com.luis.twitter.model.Tweet;

@RestController
@RequestMapping(path = "/")
public class TweetController 
{
    @Autowired
    private TweetDAO tweetDao;

    private final Logger logger = LoggerFactory.getLogger(TweetController.class);
    
    @GetMapping("/local")
    public String index() {
    	logger.debug("Tweet Controller -> Index");
    	return "index";
    }
    
    @GetMapping("local/searchAllTweets")
    public ResponseEntity<Optional<List<Tweet>>> findTweets() {
        Optional<List<Tweet>> resultList = tweetDao.getAllTweets();
        if (!resultList.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(resultList);
        }
    }
    
    @GetMapping("local/searchTweetsValidadosByUser/{usuario}")
    public ResponseEntity<Optional<List<Tweet>>> getTweetsValidadosByUser(@PathVariable String usuario) {
    	Optional<List<Tweet>> resultList = tweetDao.getTweetsValidadosByUser(usuario);
    	if (!resultList.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(resultList);
        }
    }

    @PutMapping("local/validaTweet/{id}")
    public ResponseEntity<Integer> validaTweet( @PathVariable Long id) {
    	Integer resultado = tweetDao.validaTweet(id);

    	if (resultado == 0) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(resultado);
        }
    	
    }
}
