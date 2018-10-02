package com.valhallagame.ymer.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.valhallagame.common.JS;
import com.valhallagame.recipeserviceclient.RecipeServiceClient;
import com.valhallagame.ymer.message.recipe.AddRecipeParameter;
import com.valhallagame.ymer.message.recipe.ClaimRecipeParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@RequestMapping("/v1/server-recipe")
public class ServerRecipeController {

    private final RecipeServiceClient recipeServiceClient;

    @Autowired
    ServerRecipeController(RecipeServiceClient recipeServiceClient) {
        this.recipeServiceClient = recipeServiceClient;
    }

    @PostMapping("/claim")
    @ResponseBody
    public ResponseEntity<JsonNode> claim(@RequestBody ClaimRecipeParameter input) throws IOException {
        return JS.message(recipeServiceClient.claimRecipe(input.getCharacterName(), input.getRecipe(), input.getCurrencies()));
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<JsonNode> add(@RequestBody AddRecipeParameter input) throws IOException {
        return JS.message(recipeServiceClient.addRecipe(input.getCharacterName(), input.getRecipe()));
    }
}
