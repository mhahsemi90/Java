package com.fanap.hcm.core.hcmcore.pcn.services.mapper;

import com.fanap.hcm.core.hcmcore.pcn.repository.entity.OutputParameter;
import com.fanap.hcm.core.hcmcore.pcn.services.inputs.OutputParameterInput;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OutputParameterInputMapper {
    OutputParameter mapToOutputParameter(OutputParameterInput outputParameterInput);
}
