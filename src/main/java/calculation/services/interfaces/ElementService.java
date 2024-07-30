package calculation.services.interfaces;

import calculation.repository.entity.Element;
import calculation.services.inputs.ElementInput;

public interface ElementService {
    Element persistElement(ElementInput elementInput);

    Element findElementById(Long id);

    Element findElementByVrIdAndByElementType(String vrId, String elementTypeCode);

    void deleteElementById(Long id);
}
