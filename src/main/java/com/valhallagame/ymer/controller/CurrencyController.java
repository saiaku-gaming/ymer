package com.valhallagame.ymer.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.valhallagame.common.JS;
import com.valhallagame.currencyserviceclient.CurrencyServiceClient;
import com.valhallagame.ymer.message.currency.GetCurrenciesParameter;
import com.valhallagame.ymer.message.currency.GetCurrencyParameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@RequestMapping("/v1/currency")
public class CurrencyController {
    private static final Logger logger = LoggerFactory.getLogger(CurrencyController.class);

    @Autowired
    private CurrencyServiceClient currencyServiceClient;

    @PostMapping("/get-currency")
    @ResponseBody
    public ResponseEntity<JsonNode> getCurrency(@RequestBody GetCurrencyParameter input) throws IOException {
        logger.info("Get Currency called with {}", input);
        return JS.message(currencyServiceClient.getCurrency(input.getCharacterName(), input.getCurrencyType()));
    }

    @PostMapping("/get-currencies")
    @ResponseBody
    public ResponseEntity<JsonNode> getCurrencies(@RequestBody GetCurrenciesParameter input) throws IOException {
        logger.info("Get Currencies called with {}", input);
        return JS.message(currencyServiceClient.getCurrencies(input.getCharacterName()));
    }
}
