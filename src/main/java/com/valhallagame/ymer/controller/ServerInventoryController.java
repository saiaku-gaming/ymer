package com.valhallagame.ymer.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.valhallagame.common.JS;
import com.valhallagame.inventoryserviceclient.InventoryServiceClient;
import com.valhallagame.inventoryserviceclient.message.AddInventoryItemParameter;
import com.valhallagame.inventoryserviceclient.message.DeleteInventoryItemParameter;
import com.valhallagame.inventoryserviceclient.message.SetInventoryItemContentsParameter;
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
@RequestMapping("/v1/server-inventory")
public class ServerInventoryController {
    private static final Logger logger = LoggerFactory.getLogger(ServerInventoryController.class);

    @Autowired
    private InventoryServiceClient inventoryServiceClient;

    @PostMapping("/add-inventory-item")
    @ResponseBody
    public ResponseEntity<JsonNode> addInventoryItem(@Valid @RequestBody AddInventoryItemParameter input) throws IOException {
        logger.info("Add Inventory Item called with {}", input);
        return JS.message(inventoryServiceClient.addInventoryItem(input.getCharacterName(), input.getItemName(), input.getPositionX(), input.getPositionY(), input.getItemMetaData()));
    }

    @PostMapping("/delete-inventory-item")
    @ResponseBody
    public ResponseEntity<JsonNode> deleteInventoryItem(@Valid @RequestBody DeleteInventoryItemParameter input) throws IOException {
        logger.info("Delete Inventory Item called with {}", input);
        return JS.message(inventoryServiceClient.deleteInventoryItem(input.getCharacterName(), input.getPositionX(), input.getPositionY()));
    }

    @PostMapping("/set-inventory-contents")
    @ResponseBody
    public ResponseEntity<JsonNode> setInventoryContents(@Valid @RequestBody SetInventoryItemContentsParameter input) throws IOException {
        logger.info("Set Inventory Contents called with {}", input);
        return JS.message(inventoryServiceClient.setInventoryContents(input.getCharacterName(), input.getItems()));
    }
}
