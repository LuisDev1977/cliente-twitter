package com.luis.twitter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;

@Configuration
@ConditionalOnClass({ TwitterFactory.class, Twitter.class })
@EnableSwagger2
//@EnableConfigurationProperties(Twitter4jProperties.class)
public class Twitter4jAutoConfiguration {

	private static Log log = LogFactory.getLog(Twitter4jAutoConfiguration.class);
	
	@Bean
	@ConditionalOnMissingBean
	public TwitterFactory twitterFactory(){
		
		TwitterFactory tf = new TwitterFactory();
		return tf;
	}
	
	@Bean
	@ConditionalOnMissingBean
	public Twitter twitter(TwitterFactory twitterFactory){
		return twitterFactory.getInstance();
	}
    
	@Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.any())              
          .paths(PathSelectors.any())                          
          .build();                                           
    }	
}
