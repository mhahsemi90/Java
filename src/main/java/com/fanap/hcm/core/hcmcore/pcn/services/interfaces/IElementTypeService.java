package com.fanap.hcm.core.hcmcore.pcn.services.interfaces;

import com.fanap.hcm.core.hcmcore.pcn.repository.entity.ElementType;
import com.fanap.hcm.core.hcmcore.pcn.services.inputs.ElementTypeInput;

public interface IElementTypeService {
    ElementType persistElementType(ElementTypeInput elementTypeInput);

    ElementType findElementTypeById(Long id);

    ElementType findElementTypeByCode(String typeCode);

    void deleteElementTypeById(Long id);
}
