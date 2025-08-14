package es.spain.datos.utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import es.spain.datos.dto.ComboDTO;
import es.spain.datos.dto.UsuarioDTO;
import es.spain.datos.model.Usuario;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UsuarioUtils {

	public UsuarioDTO modelToForm(Usuario model) {
		UsuarioDTO retVal = new UsuarioDTO();
		retVal.setEmail(model.getEmail());
		retVal.setId(model.getId());
		retVal.setNombre(model.getNombre());
		retVal.setUuid(model.getUuid());
		return retVal;
	}

	public List<UsuarioDTO> listModelToListForm(List<Usuario> list) {
		List<UsuarioDTO> retVal = new ArrayList<>();
		for (Usuario bean : list) {
			retVal.add(modelToForm(bean));
		}
		return retVal;
	}

	public Page<UsuarioDTO> pageToPageForm(Page<Usuario> page) {

		return new PageImpl<UsuarioDTO>(listModelToListForm(page.getContent()), page.getPageable(),
				page.getTotalElements());
	}

	public Usuario formToModel(UsuarioDTO dto, Usuario model) {
		if (model == null) {
			model = new Usuario();
			model.setPassword("");
		}
		model.setId(dto.getId());
		model.setUuid(dto.getUuid());
		model.setEmail(dto.getEmail());
		model.setNombre(dto.getNombre());
		return model;
	}

	public List<ComboDTO> listToListComboForm(List<Usuario> list) {
		return list.stream().map(model -> new ComboDTO(model.getUuid(), model.getNombre()))
				.sorted(Comparator.comparing(ComboDTO::getValue)).collect(Collectors.toList());
	}
}
