package br.com.institutoatlantico.security;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import br.com.institutoatlantico.enums.Role;
import br.com.institutoatlantico.serialization.RoleDeserialization;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Usuario implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name = "nome", nullable = false)
	private String nome;
	
	@Column(name = "login", nullable = false)
	private String login;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "created_date", nullable = false)
	private Date createdDate;
	
	@Column(name = "updated_date")
	private Date updatedDate;
	
	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "is_admin", nullable = false)
	private boolean admin;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "ROLE")
	@JsonDeserialize(using = RoleDeserialization.class)
	private Set<Long> roles = new HashSet<>();

	@Transient
	private Collection<? extends GrantedAuthority> authorities;

	public Set<Role> getRoles() {
		return this.roles.stream().map(x -> Role.toEnum(x)).collect(Collectors.toSet());
	}

	public void addRole(Role role) {
		this.roles.add(role.getId());
	}
	
	public Usuario(Long id, String login, String password, String nome, String email, Set<Role> roles) {
		super();
		this.id = id;
		this.login = login;
		this.password = password;
		this.nome = nome;
		this.email = email;
		this.authorities = roles.stream().map(role -> new SimpleGrantedAuthority(role.getDescricao()))
				.collect(Collectors.toList());
	}
	
	public Long getId() {
		return id;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return login;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	public boolean hasRole(String roleDescription) {
		return getAuthorities().contains(new SimpleGrantedAuthority(roleDescription));
	}

}
