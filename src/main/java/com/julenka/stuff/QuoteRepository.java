package com.julenka.stuff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowCountCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class QuoteRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    RowMapper<Quote> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> {
        Quote quote = new Quote();
        quote.setText(resultSet.getString("text"));
        return quote;
    };

    public void save(Quote quote){
        Map<String, String> paramMap = new HashMap<String, String >();
        paramMap.put("text",quote.getText());
        template.update("insert into Quotes (text) values (:text)", paramMap);

    }
    public int count() {
        RowCountCallbackHandler countCallback = new RowCountCallbackHandler();
        template.query("select * from Quotes", countCallback);
        int rowCount = countCallback.getRowCount();
        return rowCount;
    }
    public List<Quote> findAll(){
      return template.query("select * from Quotes",ROW_MAPPER);
    }



}
