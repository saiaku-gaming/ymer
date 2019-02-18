package com.valhallagame.ymer.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.valhallagame.characterserviceclient.CharacterServiceClient;
import com.valhallagame.characterserviceclient.message.SaveEquippedItemsParameter;
import com.valhallagame.common.JS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/v1/server-character")
public class ServerCharacterController {
    private static final Logger logger = LoggerFactory.getLogger(ServerCharacterController.class);

    @Autowired
    private CharacterServiceClient characterServiceClient;

    @RequestMapping(path = "/save-equipped-items", method = RequestMethod.POST)
    public ResponseEntity<JsonNode> saveEquippedItems(@RequestBody @Valid SaveEquippedItemsParameter input) throws IOException {
        logger.info("Save Equipped Items called with {}", input);
        return JS.message(characterServiceClient.saveEquippedItems(input.getUsername(), input.getCharacterName(),
                input.getEquippedItems()));
    }
}
