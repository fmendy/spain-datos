package es.spain.datos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.spain.datos.model.Usuario;




@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>, JpaSpecificationExecutor<Usuario> {

	List<Usuario> findByActivoTrue();

	Usuario findByActivoTrueAndId(Long id);

	Usuario findByActivoTrueAndNombreEqualsIgnoreCase(String nombre);

	@Query("SELECT u  FROM Usuario u where u.nombre = :nombre")
	Usuario getUsuarioByActivoTrueAndNombreEqualsIgnoreCase(@Param("nombre") String nombre);
	
	Usuario findByActivoTrueAndNombreIgnoreCase(String nombre);

	Usuario findByActivoTrueAndNombreIgnoreCaseAndIdNot(String nombre, Long id);
	
	Usuario findByActivoTrueAndEmailIgnoreCaseAndIdNot(String email, Long id);
}
