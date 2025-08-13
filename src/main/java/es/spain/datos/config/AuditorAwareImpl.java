package es.spain.datos.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.spain.datos.model.Usuario;
import es.spain.datos.service.UsuarioService;
import es.spain.datos.utils.SecurityUtil;




@Component
public class AuditorAwareImpl implements AuditorAware<Usuario> {

	@Autowired
	private UsuarioService usuarioService;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Optional<Usuario> getCurrentAuditor() {
		if (AuditorAwareContext.isAuditingDisabled()) {
			return Optional.empty(); // No asignar auditoría en las consultas de solo lectura
		}

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!SecurityUtil.isAuthenticated()) {
			return Optional.ofNullable(new Usuario(1L)); // Usuario por defecto
		} else {
			//return Optional.ofNullable(new Usuario(1L)); // Usuario por defecto
			return Optional.ofNullable(usuarioService.getUsuarioWithoutAuditor(authentication.getName()));
		}
	}
}
