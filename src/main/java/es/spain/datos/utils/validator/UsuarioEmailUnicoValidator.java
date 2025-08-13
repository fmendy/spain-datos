package es.spain.datos.utils.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.spain.datos.dto.UsuarioDTO;
import es.spain.datos.model.Usuario;
import es.spain.datos.repository.UsuarioRepository;
import es.spain.datos.utils.Utils;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class UsuarioEmailUnicoValidator implements ConstraintValidator<ValidUsuarioEmailUnico, UsuarioDTO> {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public boolean isValid(UsuarioDTO form, ConstraintValidatorContext context) {
		String email = form.getEmail();
		Long idActual = form.getId();

		if (email == null || email.trim().isEmpty()) {
			return true; // @NotBlank lo valida
		}
		Usuario model = usuarioRepository.findByActivoTrueAndEmailIgnoreCaseAndIdNot(email.trim(), idActual);

		if (model == null) {
			return true; // No existe => válido
		}
		// Si estamos editando el mismo registro, es válido
		if (idActual != null && idActual.equals(model.getId())) {
			return true;
		}
		// Existe otro con el mismo nombre y mismo padre
		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate(Utils.getMessage("usuario.form.email.duplicado"))
				.addPropertyNode("email").addConstraintViolation();
		return false;
	}
}