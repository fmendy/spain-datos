package es.spain.datos.model;

import java.io.Serializable;

import org.hibernate.envers.Audited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Audited
public abstract class Maestra extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1623328377059858749L;

	@Column(name = "nombre", length = 255, nullable = false)
	private String nombre;
	
	@Column(name = "descripcion", length = 255)
	private String descripcion;
	
	@Column(name = "codigo", length = 255, nullable = false)
	private String codigo;

	public Maestra(Long id) {
		super(id);
	}

}
