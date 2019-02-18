package com.valhallagame.ymer.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.valhallagame.bankserviceclient.BankServiceClient;
import com.valhallagame.bankserviceclient.message.AddBankItemParameter;
import com.valhallagame.bankserviceclient.message.DeleteBankItemParameter;
import com.valhallagame.bankserviceclient.message.SetBankItemContentsParameter;
import com.valhallagame.common.JS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/v1/server-bank")
public class ServerBankController {
    private static final Logger logger = LoggerFactory.getLogger(ServerBankController.class);

    @Autowired
    private BankServiceClient bankServiceClient;

    @PostMapping("/add-bank-item")
    @ResponseBody
    public ResponseEntity<JsonNode> addBankItem(@Valid @RequestBody AddBankItemParameter input) throws IOException {
        logger.info("Add Bank Item called with {}", input);
        return JS.message(bankServiceClient.addBankItem(input.getCharacterName(), input.getItemName(), input.getPositionX(), input.getPositionY()));
    }

    @PostMapping("/delete-bank-item")
    @ResponseBody
    public ResponseEntity<JsonNode> deleteBankItem(@Valid @RequestBody DeleteBankItemParameter input) throws IOException {
        logger.info("Delete Bank Item called with {}", input);
        return JS.message(bankServiceClient.deleteBankItem(input.getCharacterName(), input.getPositionX(), input.getPositionY()));
    }

    @PostMapping("/set-bank-contents")
    @ResponseBody
    public ResponseEntity<JsonNode> setBankContents(@Valid @RequestBody SetBankItemContentsParameter input) throws IOException {
        logger.info("Set Bank Contents called with {}", input);
        return JS.message(bankServiceClient.setBankContents(input.getCharacterName(), input.getItems()));
    }
}
