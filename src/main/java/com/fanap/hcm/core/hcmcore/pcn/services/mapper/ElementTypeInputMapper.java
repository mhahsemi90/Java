package com.fanap.hcm.core.hcmcore.pcn.services.mapper;

import com.fanap.hcm.core.hcmcore.pcn.repository.entity.ElementType;
import com.fanap.hcm.core.hcmcore.pcn.services.inputs.ElementTypeInput;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ElementTypeInputMapper {

    ElementType mapToElementType(ElementTypeInput elementTypeInput);
}
