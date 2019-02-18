package com.valhallagame.ymer.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.valhallagame.common.JS;
import com.valhallagame.statisticsserviceclient.StatisticsServiceClient;
import com.valhallagame.statisticsserviceclient.message.IncrementIntCounterParameter;
import com.valhallagame.statisticsserviceclient.message.UpdateHighTimerParameter;
import com.valhallagame.statisticsserviceclient.message.UpdateLowTimerParameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

@Controller
@RequestMapping("/v1/server-statistics")
public class ServerStatisticsController {
	private static final Logger logger = LoggerFactory.getLogger(ServerStatisticsController.class);

	@Autowired
	private StatisticsServiceClient statisticsServiceClient;

	@RequestMapping(path = "/increment-int-counter", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> incrementIntCounter(@RequestBody IncrementIntCounterParameter input)
			throws IOException {
		logger.info("Increment Int Counter called with {}", input);
		return JS.message(statisticsServiceClient.incrementIntCounter(input.getCharacterName(), input.getKey(),
				input.getValue()));
	}

	@RequestMapping(path = "/update-low-timer", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> updateLowTimer(@RequestBody UpdateLowTimerParameter input) throws IOException {
		logger.info("Update Low Timer called with {}", input);
		return JS.message(statisticsServiceClient.updateLowTimer(input.getCharacterName(), input.getKey(),
				input.getTimer()));
	}

	@RequestMapping(path = "/update-high-timer", method = RequestMethod.POST)
	public ResponseEntity<JsonNode> updateHighTimer(@RequestBody UpdateHighTimerParameter input) throws IOException {
		logger.info("Update High Timer called with {}", input);
		return JS.message(statisticsServiceClient.updateHighTimer(input.getCharacterName(), input.getKey(),
				input.getTimer()));
	}

}
