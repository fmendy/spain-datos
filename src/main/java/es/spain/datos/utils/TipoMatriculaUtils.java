package es.spain.datos.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import es.spain.datos.dto.ComboDTO;
import es.spain.datos.dto.TipoMatriculaDTO;
import es.spain.datos.model.TipoMatricula;


public class TipoMatriculaUtils extends MaestraUtils<TipoMatriculaDTO, TipoMatricula> {
	
	public static final TipoMatriculaUtils INSTANCE = new TipoMatriculaUtils();
	
	@Override
	public TipoMatriculaDTO modelToDTO(TipoMatricula model) {
		TipoMatriculaDTO dto = new TipoMatriculaDTO();
		dto.setCodigo(model.getCodigo());
		dto.setDescripcion(model.getDescripcion());
		dto.setId(model.getId());
		dto.setUuid(model.getUuid());
		return dto;
	}

	@Override
	public List<TipoMatriculaDTO> listModelToListForm(List<TipoMatricula> list) {
		List<TipoMatriculaDTO> retVal = new ArrayList<>();
		for (TipoMatricula bean : list) {
			retVal.add(modelToDTO(bean));
		}
		return retVal;
	}

	@Override
	public Page<TipoMatriculaDTO> pageToPageDTO(Page<TipoMatricula> page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TipoMatricula dtoToModel(TipoMatriculaDTO dto, TipoMatricula model) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ComboDTO> listToListComboDTO(List<TipoMatricula> list) {
		List<ComboDTO> retVal = new ArrayList<>();
		for (TipoMatricula bean : list) {
			retVal.add(new ComboDTO(bean.getUuid(), bean.getDescripcion()));
		}
		Utils.orderComboForm(retVal);
		return retVal;
	}
	

}

