package com.fanap.hcm.core.hcmcore.pcn.services.impl;

import com.fanap.hcm.core.hcmcore.pcn.repository.entity.Element;
import com.fanap.hcm.core.hcmcore.pcn.repository.service.interfaces.IElementRepository;
import com.fanap.hcm.core.hcmcore.pcn.services.inputs.ElementInput;
import com.fanap.hcm.core.hcmcore.pcn.services.interfaces.IElementService;
import com.fanap.hcm.core.hcmcore.pcn.services.mapper.ElementInputMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ElementServiceImpl implements IElementService {

    private final IElementRepository elementRepository;
    private final ElementInputMapper elementInputMapper;

    @Override
    public Element persistElement(ElementInput elementInput) {
        return elementRepository.save(
                elementInputMapper.mapToElement(elementInput)
        );
    }

    @Override
    public Element findElementById(Long id) {
        return elementRepository.findById(id).orElse(null);
    }

    @Override
    public Element findElementByVrIdAndByElementType(String vrId, String elementTypeCode) {
        return elementRepository.findElementByVrIdAndByElementTypeCode(vrId, elementTypeCode)
                .stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public void deleteElementById(Long id) {
        elementRepository.deleteById(id);
    }
}
