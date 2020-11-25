package com.julenka.controllers;

import com.julenka.stuff.Quote;
import com.julenka.stuff.QuoteRepository;
import com.julenka.stuff.QuoteValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.PostConstruct;

@Controller
public class PostController {


    @Autowired
    private QuoteRepository quoteRepository;
    @Autowired
    private QuoteValidator quoteValidator;

//todo 415 unsupported media type не понимает и не дружит с json
    @PostMapping(value = "/quote", consumes = "application/json" )
    public ResponseEntity addQuote(@RequestBody Quote quote) {
        if (quoteValidator.validate(quote.getText()) == false) {
            return ResponseEntity.status(400).build();
        }
        quoteRepository.save(quote);
        return ResponseEntity.status(200).build();
    }
}
