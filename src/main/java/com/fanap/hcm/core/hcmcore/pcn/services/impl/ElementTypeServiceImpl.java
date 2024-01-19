package com.fanap.hcm.core.hcmcore.pcn.services.impl;

import com.fanap.hcm.core.hcmcore.pcn.repository.entity.ElementType;
import com.fanap.hcm.core.hcmcore.pcn.repository.service.interfaces.IElementTypeRepository;
import com.fanap.hcm.core.hcmcore.pcn.services.inputs.ElementTypeInput;
import com.fanap.hcm.core.hcmcore.pcn.services.interfaces.IElementTypeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ElementTypeServiceImpl implements IElementTypeService {
    private final IElementTypeRepository elementTypeRepository;
    private final ModelMapper modelMapper;

    @Override
    public ElementType persistElementType(ElementTypeInput elementTypeInput) {
        return elementTypeRepository.save(
                modelMapper.map(elementTypeInput, ElementType.class)
        );
    }
}
