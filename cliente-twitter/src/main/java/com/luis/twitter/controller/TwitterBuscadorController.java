package com.luis.twitter.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luis.twitter.dao.TweetDAO;
import com.luis.twitter.model.Tweet;

@RestController
@RequestMapping(path = "/")
public class TwitterBuscadorController {

    @Autowired
    private TweetDAO tweetDao;

    private final Logger logger = LoggerFactory.getLogger(TweetController.class);
    
    @GetMapping("/twitter")
    public String index() {
    	logger.debug("Tweet Controller -> Index");
    	return "index";
    }
    
    @GetMapping("/twitter/search/{key}")
    public ResponseEntity<Optional<List<Tweet>>> findTweets( @PathVariable String key) {
        Optional<List<Tweet>> resultList = tweetDao.seleccionarTweets(key);
        if (!resultList.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(resultList);
        }
    }
    
    @GetMapping(path="/twitter/search/hashtagsMasUsados")
    public ResponseEntity<Optional<List<String>>> getHashtagsMasUsados() 
    {
    	Optional<List<String>> resultList = tweetDao.doSearchTrending();
        if (!resultList.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(resultList);
        }
        
    }
    
}

