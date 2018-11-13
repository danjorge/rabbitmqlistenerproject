package br.com.instituloatlantico.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQQueueConfiguration {
	
	private static final String QUEUE = "queue";
	
	@Bean
	Queue queue() {
		return QueueBuilder.durable(QUEUE)
					.build();
	}

}
