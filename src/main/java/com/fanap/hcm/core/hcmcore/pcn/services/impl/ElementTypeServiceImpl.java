package com.fanap.hcm.core.hcmcore.pcn.services.impl;

import com.fanap.hcm.core.hcmcore.pcn.repository.entity.ElementType;
import com.fanap.hcm.core.hcmcore.pcn.repository.service.interfaces.IElementTypeRepository;
import com.fanap.hcm.core.hcmcore.pcn.services.inputs.ElementTypeInput;
import com.fanap.hcm.core.hcmcore.pcn.services.interfaces.IElementTypeService;
import com.fanap.hcm.core.hcmcore.pcn.services.mapper.ElementTypeInputMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ElementTypeServiceImpl implements IElementTypeService {
    private final IElementTypeRepository elementTypeRepository;
    private final ElementTypeInputMapper elementTypeInputMapper;

    @Override
    public ElementType persistElementType(ElementTypeInput elementTypeInput) {
        return elementTypeRepository.save(
                elementTypeInputMapper.mapToElementType(elementTypeInput)
        );
    }

    @Override
    public ElementType findElementTypeById(Long id) {
        return elementTypeRepository.findById(id).orElse(null);
    }

    @Override
    public ElementType findElementTypeByCode(String typeCode) {
        return elementTypeRepository.findElementTypeByCode(typeCode)
                .stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public void deleteElementTypeById(Long id) {
        elementTypeRepository.deleteById(id);
    }
}
