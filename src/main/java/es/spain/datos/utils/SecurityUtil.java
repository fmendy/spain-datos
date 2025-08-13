package es.spain.datos.utils;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.context.SecurityContextHolder;

import es.spain.datos.dto.CustomUserDetailsDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SecurityUtil {

	public static CustomUserDetailsDTO getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || !authentication.isAuthenticated()) {
			throw new IllegalStateException("No hay usuario autenticado");
		}
		return (CustomUserDetailsDTO) authentication.getPrincipal();
	}

	public boolean isAuthenticated() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication != null && authentication.isAuthenticated()
				&& !(authentication instanceof AnonymousAuthenticationToken);
	}

	public static Long getCurrentUserId() {
		return getCurrentUser().getUserId();
	}

	public static Long getCurrentUserSedeId() {
		return getCurrentUser().getSedeId();
	}

	public static boolean hasAuthority(String authority) {

		return getCurrentUser().getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(authority));
	}

}
