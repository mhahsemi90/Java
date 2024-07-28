package calculation.services.mapper;

import calculation.repository.entity.Element;
import calculation.repository.entity.ElementType;
import calculation.repository.service.interfaces.IElementTypeRepository;
import calculation.services.inputs.ElementInput;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class ElementInputMapper {

    @Autowired
    protected IElementTypeRepository elementTypeRepository;

    @Mapping(
            target = "elementType",
            source = "elementType",
            qualifiedByName = "findElementTypeByCode")
    public abstract Element mapToElement(ElementInput elementInput);

    @Named("findElementTypeByCode")
    public ElementType findElementTypeByCode(String elementTypeCode) {
        return elementTypeRepository.findElementTypeByCode(elementTypeCode)
                .stream()
                .findFirst()
                .orElse(null);
    }
}
