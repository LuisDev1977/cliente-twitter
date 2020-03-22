package com.luis.twitter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = "com.luis")
public class ClienteTwitterApplication  {
    public static void main(String[] args) 
    {
    	SpringApplication.run(ClienteTwitterApplication.class, args);
    }
}

