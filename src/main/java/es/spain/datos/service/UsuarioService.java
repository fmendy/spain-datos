package es.spain.datos.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;

import es.spain.datos.dto.UsuarioDTO;
import es.spain.datos.dto.UsuarioPasswordDTO;
import es.spain.datos.dto.filter.UsuarioFilter;
import es.spain.datos.model.Usuario;
import jakarta.validation.Valid;

public interface UsuarioService extends UserDetailsService {

	UsuarioDTO getById(Long id);
	
	UsuarioPasswordDTO getUsuarioPasswordByUsuarioId(Long id);

	Long getIdUserInSession();

	Usuario getByUsername(String username);

	UserDetails loadUserByUsername(String username);

	Usuario getUsuarioWithoutAuditor(String nombre);

	Long guardar(UsuarioDTO form);
	
	Long guardarDatos(UsuarioDTO form);

	Page<UsuarioDTO> getPageSearch(UsuarioFilter filter, Pageable pageable);

	boolean isValid(BindingResult bindingResult, @Valid UsuarioDTO form);

	void eliminar(Long id);

	void cambiarPassword(UsuarioPasswordDTO form);

	boolean isValidChangePassword(BindingResult bindingResult, @Valid UsuarioPasswordDTO form);

	String getNombreById(Long id);

}
