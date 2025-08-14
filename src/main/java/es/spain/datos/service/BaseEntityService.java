package es.spain.datos.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.spain.datos.dto.BaseDTO;
import es.spain.datos.dto.filter.BaseEntityFilter;


public interface BaseEntityService<T, F extends BaseDTO,  K extends BaseEntityFilter> {

	T findById(Long id);
	
	F findDTOById(Long id);
	
	void eliminar(Long id);
	
	Long guardar(F form);
	
	Page<F> getPageSearch(K filter, Pageable pageable);
	
}
