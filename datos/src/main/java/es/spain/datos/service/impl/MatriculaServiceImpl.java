package es.spain.datos.service.impl;

import org.springframework.stereotype.Service;

import es.spain.datos.service.MatriculaService;

@Service
public class MatriculaServiceImpl implements MatriculaService {

	@Override
	public String generarMatricula() {
		return "ABC-1234"; 
	}

}
