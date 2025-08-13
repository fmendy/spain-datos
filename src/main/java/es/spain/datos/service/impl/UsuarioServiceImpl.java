package es.spain.datos.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import es.spain.datos.config.AuditorAwareContext;
import es.spain.datos.dto.CustomUserDetailsDTO;
import es.spain.datos.dto.UsuarioDTO;
import es.spain.datos.dto.UsuarioPasswordDTO;
import es.spain.datos.dto.filter.UsuarioFilter;
import es.spain.datos.dto.specifications.UsuarioSpecifications;
import es.spain.datos.model.Usuario;
import es.spain.datos.repository.UsuarioRepository;
import es.spain.datos.service.UsuarioService;
import es.spain.datos.utils.UsuarioUtil;
import es.spain.datos.utils.Utils;
import jakarta.validation.Valid;

@Service
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Page<UsuarioDTO> getPageSearch(UsuarioFilter filter, Pageable pageable) {
		return UsuarioUtil.pageToPageForm(usuarioRepository.findAll(UsuarioSpecifications.filter(filter), pageable));
	}

	@Override
	@Transactional
	public Long guardar(UsuarioDTO form) {
		Usuario model = usuarioRepository.findByActivoTrueAndId(form.getId());
		model = UsuarioUtil.formToModel(form, model);
		usuarioRepository.saveAndFlush(model);
		return model.getId();
	}

	@Override
	public UsuarioDTO getById(Long id) {
		return UsuarioUtil.modelToForm(usuarioRepository.findByActivoTrueAndId(id));
	}

	@Override
	public Usuario getByUsername(String username) {
		return usuarioRepository.findByActivoTrueAndNombreEqualsIgnoreCase(username);
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByActivoTrueAndNombreEqualsIgnoreCase(username);
		if (usuario == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}

		Set<GrantedAuthority> authorities = new java.util.HashSet<>();

		return new CustomUserDetailsDTO(usuario.getId(), usuario.getNombre(), usuario.getPassword(), authorities);
	}

	@Override
	public Long getIdUserInSession() {
		return usuarioRepository.findByActivoTrueAndNombreEqualsIgnoreCase(
				SecurityContextHolder.getContext().getAuthentication().getName()).getId();
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario getUsuarioWithoutAuditor(String nombre) {
		AuditorAwareContext.disableAuditing();
		// Accedemos al usuario sin activar auditoría
		Usuario usuario = usuarioRepository.getUsuarioByActivoTrueAndNombreEqualsIgnoreCase(nombre);
		// Habilitamos la auditoría nuevamente
		AuditorAwareContext.enableAuditing();
		return usuario;
	}

	@Override
	public boolean isValid(BindingResult bindingResult, @Valid UsuarioDTO form) {
		boolean isValid = true;
		if (bindingResult.hasErrors()) {
			return false;
		}
		if (form.getId() == null) {
			if (usuarioRepository.findByActivoTrueAndNombreIgnoreCase(form.getNombre()) != null) {
				bindingResult.rejectValue("nombre", "nombre", Utils.getMessage("usuario.form.nombre.duplicado"));
				isValid = false;
			}
		} else {
			if (usuarioRepository.findByActivoTrueAndNombreIgnoreCaseAndIdNot(form.getNombre(), form.getId()) != null) {
				bindingResult.rejectValue("nombre", "nombre", Utils.getMessage("usuario.form.nombre.duplicado"));
				isValid = false;
			}
		}

		return isValid;
	}

	@Override
	@Transactional
	public void eliminar(Long id) {
		Usuario usuario = usuarioRepository.findByActivoTrueAndId(id);
		usuario.setActivo(false);
		usuarioRepository.saveAndFlush(usuario);
	}

	@Override
	public Long guardarDatos(UsuarioDTO form) {
		Usuario model = usuarioRepository.findByActivoTrueAndId(form.getId());
		model = UsuarioUtil.formToModel(form, model);
		usuarioRepository.saveAndFlush(model);
		return model.getId();
	}

	@Override
	public UsuarioPasswordDTO getUsuarioPasswordByUsuarioId(Long id) {
		UsuarioPasswordDTO retVal = new UsuarioPasswordDTO();
		retVal.setId(usuarioRepository.findByActivoTrueAndId(id).getId());
		return retVal;
	}

	@Override
	@Transactional
	public void cambiarPassword(UsuarioPasswordDTO form) {
		Usuario model = usuarioRepository.findByActivoTrueAndId(form.getId());
		model.setPassword(passwordEncoder.encode(form.getPasswordNueva()));
		usuarioRepository.saveAndFlush(model);
	}

	@Override
	public boolean isValidChangePassword(BindingResult bindingResult, @Valid UsuarioPasswordDTO form) {
		boolean isValid = true;
		if (bindingResult.hasErrors()) {
			return false;
		}

		if (!usuarioRepository.findByActivoTrueAndId(form.getId()).getPassword()
				.equals(passwordEncoder.encode(form.getPasswordVieja()))) {
			bindingResult.rejectValue("passwordVieja", "passwordVieja",
					Utils.getMessage("usuario.form.password.vieja.no.igual"));
			isValid = false;
		}

		return isValid;
	}

	@Override
	public String getNombreById(Long id) {
		Usuario usuario = usuarioRepository.findByActivoTrueAndId(id);
		if (usuario != null) {
			return usuario.getNombre();
		}
		return null;
	}

}
