package es.spain.datos.utils;

import java.util.List;

import org.springframework.data.domain.Page;

import es.spain.datos.dto.ComboDTO;
import es.spain.datos.dto.MaestraDTO;
import es.spain.datos.model.Maestra;

public abstract class MaestraUtils<T extends MaestraDTO, F extends Maestra> {

	public abstract T modelToDTO(F model);

	public abstract List<T> listModelToListForm(List<F> list);

	public abstract Page<T> pageToPageDTO(Page<F> page);

	public abstract F dtoToModel(T dto, F model);

	public abstract List<ComboDTO> listToListComboDTO(List<F> list);
}
