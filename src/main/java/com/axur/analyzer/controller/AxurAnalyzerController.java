package com.axur.analyzer.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.axur.analyzer.AxurAnalyzerApplication;
import com.axur.analyzer.amqp.AxurAnalyzerPublisher;

@RestController
public class AxurAnalyzerController {
	
	private Logger logger = LoggerFactory.getLogger(AxurAnalyzerController.class);
	
	@Autowired
	private AxurAnalyzerPublisher publisher;

	@GetMapping("/hello")
	public String hello() throws UnknownHostException {
		logger.debug("? hello().", AxurAnalyzerApplication.APP_LOG);
		
		DateFormat df = new SimpleDateFormat("MM/dd/YYYY HH:mm:ss");
		Calendar currentCal = Calendar.getInstance();
		String currentTime = df.format(currentCal.getTime());
		
		String hostname = InetAddress.getLocalHost().getHostName();
		
		return "Axur Analyzer Under Development. Hostname: " + hostname + " Datetime: " + currentTime;
	}
	
	@GetMapping("/insert")
	public String insert() {
		logger.debug("? insert().", AxurAnalyzerApplication.APP_LOG);
		return publisher.insert();
	}
	
}
