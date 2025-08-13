package es.spain.datos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tipo_matricula")
public class TipoMatricula extends Maestra {

	private static final long serialVersionUID = 1623328377059858749L;

	public TipoMatricula(Long id) {
		super(id);
	}

}
