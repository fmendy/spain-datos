package es.spain.datos.dto;

import java.io.Serializable;

import es.spain.datos.utils.validator.ValidUsuarioEmail;
import es.spain.datos.utils.validator.ValidUsuarioEmailUnico;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ValidUsuarioEmail(message = "{usuario.form.email.no.ministerio}")
@ValidUsuarioEmailUnico(message = "{usuario.form.email.duplicado}")
public class UsuarioDTO extends MaestraDTO implements Serializable {
	
	private static final long serialVersionUID = 456352324533697712L;

	private String uuid;

	@Email
	@NotBlank
	@Size(max = 255)
	private String email;
}
