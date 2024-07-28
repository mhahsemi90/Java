package calculation.services.dto;

import calculation.repository.entity.Formula;
import calculation.repository.entity.OutputParameter;
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
    private Map<OutputParameter, String> outputParamValueMapList;
    private Map<Long, Formula> outputParamFormulaMapList;
}
