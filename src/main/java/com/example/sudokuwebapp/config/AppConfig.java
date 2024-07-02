package com.example.sudokuwebapp.config;

import com.example.sudokuwebapp.api.model.Board;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Board board() {
        return new Board(70);
    }
}
