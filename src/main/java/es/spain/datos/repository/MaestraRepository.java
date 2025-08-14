package es.spain.datos.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import es.spain.datos.model.Maestra;



@NoRepositoryBean
public interface MaestraRepository<T extends Maestra, ID extends Serializable>
		extends BaseEntityRepository<T, Long>, JpaSpecificationExecutor<T>  {

	T findByActivoTrueAndNombreContainsIgnoreCase(String nombre);

	T findByActivoTrueAndNombreIgnoreCase(String nombre);

	T findByActivoTrueAndNombreIgnoreCaseAndIdNot(String nombre, Long id);

}