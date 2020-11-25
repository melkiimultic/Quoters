package com.julenka.stuff;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class QuoteValidator {
    public boolean validate(String text){
        if(StringUtils.isEmpty(text)){
            return false;
        }
        return true;
    }

}
