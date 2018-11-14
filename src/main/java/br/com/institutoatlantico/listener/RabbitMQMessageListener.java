package br.com.institutoatlantico.listener;

import java.util.ArrayList;
import java.util.List;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.util.ObjectUtils;
import org.springframework.util.SerializationUtils;

import br.com.institutoatlantico.DTO.EmailRequestDTO;
import br.com.institutoatlantico.repository.UsuarioRepository;
import br.com.institutoatlantico.security.Usuario;

public class RabbitMQMessageListener implements MessageListener {
	
	@Autowired
    public JavaMailSender emailSender;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public void onMessage(Message message) {
		
		EmailRequestDTO dto = (EmailRequestDTO) SerializationUtils.deserialize(message.getBody());
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		
		if(dto.getUsuario() == null || ObjectUtils.isEmpty(dto.getUsuario())) {
			List<Usuario> users = (List<Usuario>) usuarioRepository.findAll();
			List<Usuario> adminUsers = new ArrayList<>();
			
			users.forEach((user) -> {
				if(user.hasRole("ADMIN")) {
					adminUsers.add(user);
				}
				
				String[] emails = new String[adminUsers.size()];
				adminUsers.forEach(adminUser -> {
					emails[users.indexOf(adminUser)] = adminUser.getEmail();
					simpleMailMessage.setTo(emails);					
				});
			});
		} else {
			simpleMailMessage.setTo(dto.getUsuario().getEmail());
		}
		
		simpleMailMessage.setText(dto.getEmail());
		
		emailSender.send(simpleMailMessage);
	}

}
