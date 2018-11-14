package br.com.institutoatlantico.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {
	
	ADMIN(1L, "ROLE_ADMIN");
	
	private Long id;
	private String descricao;
	
	
	public static Role toEnum(Long id) {
		
		if (id == null) {
			return null;
		}
		
		for (Role profile : Role.values()) {
			if (id.equals(profile.getId())) {
				return profile;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + id);
	}

}