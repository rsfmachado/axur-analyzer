package com.axur.analyzer.model;

import java.io.Serializable;

public class AxurAnalyzerInsertionMessage implements Serializable{

	private static final long serialVersionUID = 1L;
	private String client;
	private String regex;
	
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public String getRegex() {
		return regex;
	}
	public void setRegex(String regex) {
		this.regex = regex;
	}
	
}
