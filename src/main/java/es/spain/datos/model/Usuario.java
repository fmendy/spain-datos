package es.spain.datos.model;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "usuario")
@Audited
public class Usuario implements Serializable {

	private static final long serialVersionUID = 3656431595003998229L;

	public Usuario(Long id) {
		super();
		this.id = id;
	}

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "uuid", length = 255, unique = true, nullable = false, insertable = false, updatable = false)
	private String uuid;

	@Column(name = "nombre", length = 255, unique = true, nullable = false)
	private String nombre;

	@Column(name = "email", length = 255, unique = true, nullable = false)
	private String email;

	@Column(name = "password", length = 255)
	private String password;

	@Column(name = "Activo", insertable = false)
	@ColumnDefault("1")
	private boolean activo;

	@CreatedDate
	@Column(name = "fecha_creacion", insertable = false, nullable = false, updatable = false)
	@ColumnDefault("now()")
	private Date fechaCreacion;

	@LastModifiedDate
	@Column(name = "fecha_modificacion", insertable = false, nullable = false)
	@ColumnDefault("now()")
	private Date fechaModificacion;

}
