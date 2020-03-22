package com.luis.twitter.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.luis.twitter.model.Tweet;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Trend;
import twitter4j.Trends;
import twitter4j.Twitter;
import twitter4j.TwitterException;


@Repository
public class TweetDAO 
{
	private static Twitter twitter;
    
	@Value("${followers.NUM_MIN_FOLLOWERS}")
    private int numFollowers;
	
	@Value("${trends.NUM_TOP_HASHTAGS}")
    private int numTopHashtags;
	
	@Value("${trends.weoid}")
	private int weoid;
	
	@Value("#{'${idiomas.permitidos}'.split(',')}")
	private List<String> listaIdiomasPermitidos;

        
    @Autowired ITweetRepository tweetRepository;
    
    public TweetDAO(Twitter twitter) {
        this.twitter = twitter;
    }
    
    public Optional<List<Tweet>> getAllTweets(){
    	List<Tweet> resultado = new ArrayList<>();
    	resultado = tweetRepository.findAll();
    	
    	return Optional.ofNullable(resultado);
    }
    
    public Optional<List<Tweet>> getTweetsValidadosByUser(String usuario)
    {
    	List<Tweet> resultado = new ArrayList<>();
    	resultado = tweetRepository.findByUsuarioAndValidadoTrue(usuario);
		    	
    	return Optional.ofNullable(resultado);
    }
    
    public Integer validaTweet(Long id)
    {
    	Integer resultado = tweetRepository.validaTweet(id);
		    	
    	return resultado;
    }
    
    public Optional<List<Tweet>> seleccionarTweets(String key) {
        Tweet tweet = null;
    	List<Tweet> resultList = new ArrayList<Tweet>();

        Query query = new Query(key);
        QueryResult result = null;

        try {
            query.setCount(1000);
            result = twitter.search(query);
        } catch (TwitterException ex) {
            Logger.getLogger(TweetDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (result == null) {
            System.out.println("Did not find any result");
            return null;
        }
        List<Status> tweetsList = result.getTweets();
        if (!tweetsList.isEmpty()) {
        	for (Status status : tweetsList) {
        		if (status.getUser().getFollowersCount() > numFollowers &&
//        				((status.getLang().equals("es")) || (status.getLang().equals("fr")) || (status.getLang().equals("it")))) {
        				listaIdiomasPermitidos.contains(status.getLang())) {
        			tweet = new Tweet();
        			tweet.setId(status.getId());
        			tweet.setTexto(status.getText());
                    tweet.setUsuario(status.getUser().getName() != null ? status.getUser().getName() : null);
                    tweet.setLocalizacion(status.getUser().getLocation() != null ? status.getUser().getLocation() : null);
                    tweet.setValidado(Boolean.FALSE);
                    resultList.add(tweet);
        		}
        	}
        };
        
        tweetRepository.saveAll(resultList);

        return Optional.ofNullable(resultList);

    }

    public Optional<List<String>> doSearchTrending() {

    	List<String> resultList = new ArrayList<String>();

        try {
            Trends trends = twitter.getPlaceTrends(weoid);
            Trend[] arrTrend = trends.getTrends();
            
            for (int i = 0; i < numTopHashtags; i++) {
				resultList.add(arrTrend[i].getName());
			}
        } catch (TwitterException ex) {
            Logger.getLogger(TweetDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        return Optional.ofNullable(resultList);

    }
    
}
