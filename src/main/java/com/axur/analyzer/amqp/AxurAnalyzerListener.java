package com.axur.analyzer.amqp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.axur.analyzer.config.AxurAnalyzerConfig;
import com.axur.analyzer.model.AxurAnalyzerInsertionMessage;

@Component
public class AxurAnalyzerListener {

	private static final Logger logger = LoggerFactory.getLogger(AxurAnalyzerListener.class);

	@RabbitListener(queues = {AxurAnalyzerConfig.INSERTION_QUEUE})
    public void receiveMessage(AxurAnalyzerInsertionMessage message) {
    		logger.debug("[AXUR_ANALYZER_PUBLISHER] receiveMessage().");
        
    		logger.debug("[AXUR_ANALYZER_PUBLISHER] receiveMessage(). Message Client = " + message.getClient());
    		logger.debug("[AXUR_ANALYZER_PUBLISHER] receiveMessage(). Message Regex = " + message.getRegex());
    		
    		// TODO Insert into the Repository
    }
	
	// TODO Create another method to listen to another queue 
	public void validate() {
		
	}
}
