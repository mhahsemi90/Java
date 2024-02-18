package com.fanap.hcm.core.hcmcore.pcn.services.interfaces;

import com.fanap.hcm.core.hcmcore.pcn.repository.entity.Element;
import com.fanap.hcm.core.hcmcore.pcn.services.inputs.ElementInput;

public interface IElementService {
    Element persistElement(ElementInput elementInput);

    Element findElementById(Long id);

    Element findElementByVrIdAndByElementType(String vrId, String elementTypeCode);

    void deleteElementById(Long id);
}
