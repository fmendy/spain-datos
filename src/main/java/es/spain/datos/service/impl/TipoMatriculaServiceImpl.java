package es.spain.datos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.spain.datos.dto.ComboDTO;
import es.spain.datos.dto.TipoMatriculaDTO;
import es.spain.datos.dto.filter.TipoMatriculaFilter;
import es.spain.datos.model.TipoMatricula;
import es.spain.datos.repository.TipoMatriculaRepository;
import es.spain.datos.service.TipoMatriculaService;
import es.spain.datos.utils.TipoMatriculaUtils;

@Service
public class TipoMatriculaServiceImpl implements TipoMatriculaService {
	
	@Autowired
	private TipoMatriculaRepository tipoMatriculaRepository;

	@Override
	public List<ComboDTO> getListComboDTO() {
		List<TipoMatricula> list = tipoMatriculaRepository.findByActivoTrue();
		return TipoMatriculaUtils.INSTANCE.listToListComboDTOByCodigo(list);
	}

	@Override
	public TipoMatricula findById(Long id) {
		return tipoMatriculaRepository.findByActivoTrueAndId(id);
	}

	@Override
	public TipoMatriculaDTO findDTOById(Long id) {
		return null;
	}

	@Override
	public void eliminar(Long id) {
		TipoMatricula tipoMatricula = tipoMatriculaRepository.findByActivoTrueAndId(id);
		tipoMatricula.setActivo(false);
		tipoMatriculaRepository.save(tipoMatricula);
	}

	@Override
	public Long guardar(TipoMatriculaDTO form) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<TipoMatriculaDTO> getPageSearch(TipoMatriculaFilter filter, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

}
