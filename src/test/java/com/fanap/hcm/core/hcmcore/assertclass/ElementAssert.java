package com.fanap.hcm.core.hcmcore.assertclass;

import com.fanap.hcm.core.hcmcore.pcn.repository.entity.Element;
import org.assertj.core.api.AbstractAssert;

public class ElementAssert extends AbstractAssert<ElementAssert, Element> {
    protected ElementAssert(Element element) {
        super(element, ElementAssert.class);
    }

    public static ElementAssert assertThat(Element actual) {
        return new ElementAssert(actual);
    }
}
