package br.com.instituloatlantico.config;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQExchangeConfiguration {

	@Bean
	Exchange exchange() {
		return ExchangeBuilder.directExchange("email-exchange").build();
	}
}
