package es.spain.datos.dto;

import java.io.Serializable;

import es.spain.datos.utils.validator.ValidPassword;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioPasswordDTO implements Serializable {

	private static final long serialVersionUID = 1833256806120965218L;

	private Long id;

	@NotBlank
	@Size(max = 255)
	private String passwordVieja;

	@NotBlank
	@Size(min = 8, max = 255)
	@ValidPassword
	private String passwordNueva;

	@NotBlank
	@Size(min = 8, max = 255)
	private String passwordNuevaCopia;

}
