package es.spain.datos.dto;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetailsDTO implements UserDetails {

	private static final long serialVersionUID = -8698409853397020145L;
	private Long userId;
	private Long sedeId;
	private String username;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;

	// getters, setters, etc.

	public CustomUserDetailsDTO(Long userId,  String username, String password,
			Set<GrantedAuthority> authorities) {
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.authorities = authorities;
	}

	public Long getUserId() {
		return userId;
	}

	public Long getSedeId() {
		return sedeId;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

}
