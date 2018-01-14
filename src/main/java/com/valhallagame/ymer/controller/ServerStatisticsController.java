package com.valhallagame.ymer.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.JsonNode;
import com.valhallagame.common.JS;
import com.valhallagame.statisticsserviceclient.StatisticsServiceClient;
import com.valhallagame.statisticsserviceclient.message.IncrementIntCounterParameter;


@Controller
@RequestMapping("/v1/server-statistics")
public class ServerStatisticsController {

	@Autowired
	StatisticsServiceClient statisticsServiceClient;

	@RequestMapping(path = "/increment-int-counter", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> getFriendData(@RequestBody IncrementIntCounterParameter input) throws IOException {
		return JS.message(statisticsServiceClient.incrementIntCounter(input.getCharacterName(), input.getKey(), input.getValue()));
	}

}
