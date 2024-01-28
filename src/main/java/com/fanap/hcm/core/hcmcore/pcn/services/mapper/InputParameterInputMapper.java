package com.fanap.hcm.core.hcmcore.pcn.services.mapper;

import com.fanap.hcm.core.hcmcore.pcn.repository.entity.InputParameter;
import com.fanap.hcm.core.hcmcore.pcn.services.inputs.InputParameterInput;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InputParameterInputMapper {
    InputParameter mapToInputParameter(InputParameterInput inputParameterInput);
}
