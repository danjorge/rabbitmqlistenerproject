package br.com.institutoatlantico.serialization;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import br.com.institutoatlantico.enums.Role;

public class RoleDeserialization extends JsonDeserializer<Set<Long>> {

	@Override
	public Set<Long> deserialize(JsonParser jsonParser, DeserializationContext arg1)
			throws IOException, JsonProcessingException {
		 List<String> listaRolesString = jsonParser.readValueAs(List.class);
		 Set<Long> roles = listaRolesString.stream().map(roleName -> Role.valueOf(roleName).getId()).collect(Collectors.toSet());
		return roles;
	}

}