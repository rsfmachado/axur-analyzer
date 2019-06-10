package com.axur.analyzer.model;

import java.io.Serializable;

public class AxurAnalyzerValidationMessage implements Serializable{

	private static final long serialVersionUID = 1L;
	private String client;
	private String url;
	private String correlationId;
	
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCorrelationId() {
		return correlationId;
	}
	public void setCorrelationId(String correlationId) {
		this.correlationId = correlationId;
	}
	
	
}
