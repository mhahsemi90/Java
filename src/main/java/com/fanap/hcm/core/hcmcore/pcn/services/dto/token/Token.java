package com.fanap.hcm.core.hcmcore.pcn.services.dto.token;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Token implements Cloneable {
    private TokenType tokenType;
    private String value;
    private Integer level;

    @Override
    public Token clone() {
        try {
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return (Token) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
