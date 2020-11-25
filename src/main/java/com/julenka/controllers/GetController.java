package com.julenka.controllers;

import com.julenka.stuff.Quote;
import com.julenka.stuff.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Random;

@Controller
public class GetController {
    @Autowired
    private QuoteRepository quoteRepository;


    @GetMapping(value = "/quotes")
    public ResponseEntity<List<Quote>>  getAllQuotes(){
        if(quoteRepository.count() == 0){
            return ResponseEntity.status(404).build();
        }
        List<Quote> collected = quoteRepository.findAll();
        return ResponseEntity.status(200).body(collected);
    }
    @GetMapping(value = "/quote")
    public ResponseEntity<Quote> getRandomQuote(){

        if(quoteRepository.count() == 0){
            return ResponseEntity.status(404).build();
        }
        List<Quote> collected = quoteRepository.findAll();
        Random random = new Random();
        Quote quote = collected.get(random.nextInt(collected.size()));
        return ResponseEntity.status(200).body(quote);
    }
}
