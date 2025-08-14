package es.spain.datos.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import es.spain.datos.model.BaseEntity;



@NoRepositoryBean
public interface BaseEntityRepository<T extends BaseEntity, ID extends Serializable>
		extends JpaRepository<T, Long>, JpaSpecificationExecutor<T> {

	List<T> findByActivoTrue();

	T findByActivoTrueAndId(Long id);
	
	T findByActivoTrueAndUuid(String uuid);
}