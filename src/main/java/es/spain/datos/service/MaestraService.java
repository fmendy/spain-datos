package es.spain.datos.service;

import java.util.List;

import es.spain.datos.dto.ComboDTO;
import es.spain.datos.dto.MaestraDTO;
import es.spain.datos.dto.filter.MaestraFilter;

public interface MaestraService<T, F extends MaestraDTO, K extends MaestraFilter>
        extends BaseEntityService<T, F, K> {    
    
    List<ComboDTO> getListComboForm();
}
