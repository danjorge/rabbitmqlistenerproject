package br.com.institutoatlantico.DTO;

import java.io.Serializable;

import br.com.institutoatlantico.security.Usuario;
import lombok.Data;

@Data
public class EmailRequestDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String email;
	private Usuario usuario;
}
