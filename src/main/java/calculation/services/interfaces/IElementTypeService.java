package calculation.services.interfaces;

import calculation.repository.entity.ElementType;
import calculation.services.inputs.ElementTypeInput;

public interface IElementTypeService {
    ElementType persistElementType(ElementTypeInput elementTypeInput);

    ElementType findElementTypeById(Long id);

    ElementType findElementTypeByCode(String typeCode);

    void deleteElementTypeById(Long id);
}
