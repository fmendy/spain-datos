package es.spain.datos.model;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode
@NoArgsConstructor
@Audited
public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = -5526430364598206935L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "uuid", length = 255, unique = true, nullable = false, insertable = false, updatable = false)
	private String uuid;

	@Column(name = "activo")
	private boolean activo = true;

	@CreatedDate
	@Column(name = "fecha_creacion", nullable = false, updatable = false)
	private Date fechaCreacion;

	@LastModifiedDate
	@Column(name = "fecha_modificacion", nullable = false)
	private Date fechaModificacion;

	@CreatedBy
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario_creacion", nullable = false)
	private Usuario usuarioCreacion;

	@LastModifiedBy
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario_modificacion", nullable = false)
	private Usuario usuarioModificacion;

	public BaseEntity(Long id) {
		super();
		this.id = id;
	}

}
