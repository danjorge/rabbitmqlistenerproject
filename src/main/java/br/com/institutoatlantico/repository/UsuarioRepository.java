package br.com.institutoatlantico.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.institutoatlantico.security.Usuario;

public interface UsuarioRepository extends PagingAndSortingRepository<Usuario, Long> {
	
	
}
