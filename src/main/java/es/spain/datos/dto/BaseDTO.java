package es.spain.datos.dto;

import java.io.Serializable;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class BaseDTO implements Serializable {

	private static final long serialVersionUID = -6055730925626631566L;

	private Long id;
	
	@Size(max = 255)
	private String uuid;
}
