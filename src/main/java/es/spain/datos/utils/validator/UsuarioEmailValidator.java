package es.spain.datos.utils.validator;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import es.spain.datos.dto.UsuarioDTO;
import es.spain.datos.utils.Utils;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class UsuarioEmailValidator implements ConstraintValidator<ValidUsuarioEmail, UsuarioDTO> {

	@Override
	public boolean isValid(UsuarioDTO form, ConstraintValidatorContext context) {
		String email = form.getEmail();

		if (email == null || email.trim().isEmpty()) {
			return true; // @NotBlank lo valida
		}

		String regex = "^[\\w.-]+@(transportes\\.gob\\.es|externomf\\.es)$";;
		if (Pattern.matches(regex, email)) {
			return true;
		}

		// Existe otro con el mismo nombre y mismo padre
		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate(Utils.getMessage("usuario.form.email.no.ministerio"))
				.addPropertyNode("email").addConstraintViolation();
		return false;

	}
}