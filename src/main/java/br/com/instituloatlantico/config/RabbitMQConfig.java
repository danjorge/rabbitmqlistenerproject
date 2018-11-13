package br.com.instituloatlantico.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.instituloatlantico.listener.RabbitMQMessageListener;

@Configuration
public class RabbitMQConfig {
	
	@Bean
	Binding binding() {
		return BindingBuilder
					.bind(new RabbitMQQueueConfiguration().queue())
					.to(new RabbitMQExchangeConfiguration().exchange())
					.with("email")
					.noargs();
				
	}
	
	@Bean
	ConnectionFactory connectionFactory() {
		CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory("localhost");
		cachingConnectionFactory.setUsername("guest");
		cachingConnectionFactory.setPassword("guest");
		
		return cachingConnectionFactory;
	}
	
	@Bean
	MessageListenerContainer messageListenerContainer() {
		SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
		simpleMessageListenerContainer.setConnectionFactory(connectionFactory());
		simpleMessageListenerContainer.addQueues(new RabbitMQQueueConfiguration().queue());
		simpleMessageListenerContainer.setMessageListener(new RabbitMQMessageListener());
		
		return simpleMessageListenerContainer;
	}
}
