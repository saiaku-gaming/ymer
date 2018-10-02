package com.valhallagame.ymer.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.valhallagame.common.JS;
import com.valhallagame.recipeserviceclient.RecipeServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@RequestMapping("/v1/server-recipe")
public class RecipeController {

    private final RecipeServiceClient recipeServiceClient;

    @Autowired
    RecipeController(RecipeServiceClient recipeServiceClient) {
        this.recipeServiceClient = recipeServiceClient;
    }

    @GetMapping("/get")
    @ResponseBody
    public ResponseEntity<JsonNode> get(@RequestAttribute("username") String username) throws IOException {
        return JS.message(recipeServiceClient.getRecipes(username));
    }
}
