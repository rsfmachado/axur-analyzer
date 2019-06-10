package com.axur.analyzer.amqp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.axur.analyzer.AxurAnalyzerApplication;
import com.axur.analyzer.config.AxurAnalyzerConfig;
import com.axur.analyzer.model.AxurAnalyzerInsertionMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class AxurAnalyzerPublisher {
	
	private Logger logger = LoggerFactory.getLogger(AxurAnalyzerPublisher.class);

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	public String insert() {
		logger.debug("? insert().", AxurAnalyzerApplication.APP_LOG);
		
		AxurAnalyzerInsertionMessage insertionMessage = new AxurAnalyzerInsertionMessage();
		insertionMessage.setClient("clientTest");
		insertionMessage.setRegex("regex test");
		
	    try {
	         String messageJson = objectMapper.writeValueAsString(insertionMessage);
	         Message message = MessageBuilder
	                                .withBody(messageJson.getBytes())
	                                .setContentType(MessageProperties.CONTENT_TYPE_JSON)
	                                .build();
	         this.rabbitTemplate.convertAndSend(AxurAnalyzerConfig.REQUEST_EXCHANGE, AxurAnalyzerConfig.INSERTION_ROUTING_KEY, message);
	     } catch (JsonProcessingException e) {
	         e.printStackTrace();
	     }
		
		DateFormat df = new SimpleDateFormat("MM/dd/YYYY HH:mm:ss");
		Calendar currentCal = Calendar.getInstance();
		return "Axur Analyzer Message published in RabbitMQ Exchange ["+AxurAnalyzerConfig.REQUEST_EXCHANGE+"] with RoutingKey ["+AxurAnalyzerConfig.INSERTION_ROUTING_KEY+"] at " + df.format(currentCal.getTime());
	}
	
}
