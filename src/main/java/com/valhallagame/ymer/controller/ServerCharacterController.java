package com.valhallagame.ymer.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.valhallagame.characterserviceclient.CharacterServiceClient;
import com.valhallagame.characterserviceclient.message.EquipItemParameter;
import com.valhallagame.characterserviceclient.message.UnequipItemParameter;
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

    @RequestMapping(path = "/equip-item", method = RequestMethod.POST)
    public ResponseEntity<JsonNode> equipItem(@RequestBody @Valid EquipItemParameter input) throws IOException {
        logger.info("Equip Item called with {}", input);
        return JS.message(characterServiceClient.equipItem(input.getCharacterName(),
                input.getItemToEquip()));
    }

    @RequestMapping(path = "/unequip-item", method = RequestMethod.POST)
    public ResponseEntity<JsonNode> unequipItem(@RequestBody @Valid UnequipItemParameter input) throws IOException {
        logger.info("Unequip Item called with {}", input);
        return JS.message(characterServiceClient.unequipItem(input.getCharacterName(),
                input.getItemSlot()));
    }
}
