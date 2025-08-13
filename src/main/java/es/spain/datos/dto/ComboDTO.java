package es.spain.datos.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ComboDTO implements Serializable {

	private static final long serialVersionUID = -9080075074508267861L;

	public ComboDTO(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}

	private String key;

	private String value;
}
