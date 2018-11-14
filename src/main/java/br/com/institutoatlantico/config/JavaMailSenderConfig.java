package br.com.institutoatlantico.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.institutoatlantico.model.Email;

@Configuration
@ConfigurationProperties
public class JavaMailSenderConfig {

	@Bean
	public Email getJavaMailSender() {
	    Email mailSender = new Email();
	    
	    mailSender.setHost("smtp.gmail.com");
	    mailSender.setPort(587);
	     
	    mailSender.setUsername("my.gmail@gmail.com");
	    mailSender.setPassword("password");
	    
	    mailSender.setAuth(true);
	    mailSender.setStarttlsEnable(true);
	    
	    return mailSender;
	}
}
