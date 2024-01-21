package com.fanap.hcm.core.hcmcore.pcn.services.impl;

import com.fanap.hcm.core.hcmcore.pcn.repository.entity.Element;
import com.fanap.hcm.core.hcmcore.pcn.repository.entity.ElementType;
import com.fanap.hcm.core.hcmcore.pcn.repository.service.interfaces.IElementRepository;
import com.fanap.hcm.core.hcmcore.pcn.repository.service.interfaces.IElementTypeRepository;
import com.fanap.hcm.core.hcmcore.pcn.services.inputs.ElementInput;
import com.fanap.hcm.core.hcmcore.pcn.services.interfaces.IElementService;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Service;

@Service
public class ElementServiceImpl implements IElementService {

    private final IElementRepository elementRepository;
    private final IElementTypeRepository elementTypeRepository;
    private final ModelMapper modelMapper;

    public ElementServiceImpl(IElementRepository elementRepository, IElementTypeRepository elementTypeRepository, ModelMapper modelMapper) {
        this.elementRepository = elementRepository;
        this.elementTypeRepository = elementTypeRepository;
        this.modelMapper = modelMapper;
        Converter<String, ElementType> getElementTypeByCode = mappingContext ->
                elementTypeRepository.findElementTypeByCode(mappingContext.getSource())
                        .stream()
                        .findFirst()
                        .orElse(null);
        TypeMap<ElementInput, Element> typeMap = modelMapper.createTypeMap(ElementInput.class, Element.class);
        typeMap.addMappings(mapper -> mapper.using(getElementTypeByCode).map(ElementInput::getElementType, Element::setElementType));
    }

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
                modelMapper.map(elementInput, Element.class)
        );
    }
}
