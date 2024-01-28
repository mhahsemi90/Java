package com.fanap.hcm.core.hcmcore.pcn.services.mapper;

import com.fanap.hcm.core.hcmcore.pcn.repository.entity.Element;
import com.fanap.hcm.core.hcmcore.pcn.repository.entity.ElementType;
import com.fanap.hcm.core.hcmcore.pcn.repository.service.interfaces.IElementTypeRepository;
import com.fanap.hcm.core.hcmcore.pcn.services.inputs.ElementInput;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class ElementInputMapper {

    @Autowired
    protected IElementTypeRepository elementTypeRepository;

    @Mapping(
            target = "elementType",
            source = "elementType",
            qualifiedByName = "findElementTypeByCode")
    public abstract Element mapToElement(ElementInput elementInput);

    @Named("findElementTypeByCode")
    public ElementType findElementTypeByCode(String elementTypeCode) {
        return elementTypeRepository.findElementTypeByCode(elementTypeCode)
                .stream()
                .findFirst()
                .orElse(null);
    }
}
