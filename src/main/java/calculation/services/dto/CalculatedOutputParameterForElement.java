package calculation.services.dto;

import calculation.repository.entity.Formula;
import calculation.services.dto.entity.FormulaDto;
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
public class CalculatedOutputParameterForElement {
    private Long elementId;
    private Map<OutputParameterDto, String> outputParamValueMapList;
    private Map<Long, FormulaDto> outputParamFormulaMapList;
}
