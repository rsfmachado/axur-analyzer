package com.axur.analyzer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.messaging.handler.annotation.support.MessageHandlerMethodFactory;

@Configuration
public class AxurAnalyzerConfig implements RabbitListenerConfigurer {
	
	public static final String INSERTION_QUEUE = "insertion.queue";
	public static final String VALIDATION_QUEUE = "validation.queue";
	public static final String RESPONSE_QUEUE = "response.queue";
	
	public static final String REQUEST_EXCHANGE ="request.exchange";
	public static final String RESPONSE_EXCHANGE = "response.exchange";
	
	public static final String INSERTION_ROUTING_KEY = "insertion.routing.key";
	public static final String VALIDATION_ROUTING_KEY = "validation.routing.key";
	public static final String RESPONSE_ROUTING_KEY = "response.routing.key";
	
	@Bean
	Queue insertionQueue() {
		return new Queue(INSERTION_QUEUE);
	}
	
	@Bean
	Queue validationQueue() {
		return new Queue(VALIDATION_QUEUE);
	}
	
	@Bean
	Queue responseQueue() {
		return new Queue(RESPONSE_QUEUE);
	}
	
	@Bean
	DirectExchange requestExchange() {
		return new DirectExchange(REQUEST_EXCHANGE);
	}
	
	@Bean
	DirectExchange responseExchange() {
		return new DirectExchange(RESPONSE_EXCHANGE);
	}
	
	@Bean
	Binding insertionBinding(Queue insertionQueue, DirectExchange requestExchange) {
		return BindingBuilder.bind(insertionQueue).to(requestExchange).with(INSERTION_ROUTING_KEY);
	}
	
	@Bean
	Binding validationBinding(Queue validationQueue, DirectExchange requestExchange) {
		return BindingBuilder.bind(validationQueue).to(requestExchange).with(VALIDATION_ROUTING_KEY);
	}
	
	@Bean
	Binding responseBinding(Queue responseQueue, DirectExchange responseExchange) {
		return BindingBuilder.bind(responseQueue).to(responseExchange).with(RESPONSE_ROUTING_KEY);
	}
		
    @Bean
    MessageHandlerMethodFactory messageHandlerMethodFactory() {
        DefaultMessageHandlerMethodFactory messageHandlerMethodFactory = new DefaultMessageHandlerMethodFactory();
        messageHandlerMethodFactory.setMessageConverter(consumerJackson2MessageConverter());
        return messageHandlerMethodFactory;
    }

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {
        registrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
    }

    @Bean
    public MappingJackson2MessageConverter consumerJackson2MessageConverter() {
        return new MappingJackson2MessageConverter();
    }
}
