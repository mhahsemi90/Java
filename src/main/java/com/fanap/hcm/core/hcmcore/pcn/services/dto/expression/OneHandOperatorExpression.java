package com.fanap.hcm.core.hcmcore.pcn.services.dto.expression;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OneHandOperatorExpression extends OperatorExpression {
    private Expression argument;
}
