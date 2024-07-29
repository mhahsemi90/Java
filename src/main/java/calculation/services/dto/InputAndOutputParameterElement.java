package calculation.services.dto;

import calculation.services.dto.entity.FormulaDto;
import calculation.services.dto.entity.InputParameterDto;
import calculation.services.dto.entity.OutputParameterDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InputAndOutputParameterElement {
    private Long elementId;
    private Map<InputParameterDto, String> inputParamMapList;
    private Map<OutputParameterDto, FormulaDto> outputParamMapList;
}
