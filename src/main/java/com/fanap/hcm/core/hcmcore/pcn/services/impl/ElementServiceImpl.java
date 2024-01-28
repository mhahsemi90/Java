package com.fanap.hcm.core.hcmcore.pcn.services.impl;

import com.fanap.hcm.core.hcmcore.pcn.repository.entity.Element;
import com.fanap.hcm.core.hcmcore.pcn.repository.entity.ElementType;
import com.fanap.hcm.core.hcmcore.pcn.repository.service.interfaces.IElementRepository;
import com.fanap.hcm.core.hcmcore.pcn.repository.service.interfaces.IElementTypeRepository;
import com.fanap.hcm.core.hcmcore.pcn.services.inputs.ElementInput;
import com.fanap.hcm.core.hcmcore.pcn.services.interfaces.IElementService;
import com.fanap.hcm.core.hcmcore.pcn.services.mapper.ElementInputMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ElementServiceImpl implements IElementService {

    private final IElementRepository elementRepository;
    private final IElementTypeRepository elementTypeRepository;
    private final ElementInputMapper elementInputMapper;

    @Override
    public Element findElementById(Long id) {
        return elementRepository.findById(id).orElse(null);
    }

    @Override
    public Element findElementByVrIdAndByElementType(String vrId, String elementTypeCode) {
        ElementType elementType = elementTypeRepository.findElementTypeByCode(elementTypeCode)
                .stream()
                .findFirst()
                .orElse(null);
        return elementRepository.findElementByVrIdAndByElementType(vrId, elementType)
                .stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public Element persistElement(ElementInput elementInput) {
        return elementRepository.save(
                elementInputMapper.mapToElement(elementInput)
        );
    }
}
