package com.carmowallison.voyage.config;

import java.text.ParseException;

import com.carmowallison.voyage.services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;



@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private DBService dbService;

    @Bean
    public boolean intastianteDatabase() throws ParseException {
        System.out.println("Inicia carga");
        dbService.instantieateTestDatabase();
        System.out.println("Finaliza carga");
        return true;
    }
}
