package com.valhallagame.ymer.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.valhallagame.common.JS;
import com.valhallagame.currencyserviceclient.CurrencyServiceClient;
import com.valhallagame.currencyserviceclient.message.AddCurrencyParameter;
import com.valhallagame.currencyserviceclient.message.SubtractCurrencyParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@RequestMapping("/v1/server-currency")
public class ServerCurrencyController {
    @Autowired
    private CurrencyServiceClient currencyServiceClient;

    @PostMapping("/add-currency")
    @ResponseBody
    public ResponseEntity<JsonNode> addCurrency(@RequestBody AddCurrencyParameter input) throws IOException {
        return JS.message(currencyServiceClient.addCurrency(input.getCharacterName(), input.getCurrencyType(), input.getAmount()));
    }

    @PostMapping("/subtract-currency")
    @ResponseBody
    public ResponseEntity<JsonNode> subtractCurrency(@RequestBody SubtractCurrencyParameter input) throws IOException {
        return JS.message(currencyServiceClient.subtractCurrency(input.getCharacterName(), input.getCurrencyType(), input.getAmount()));
    }
}
