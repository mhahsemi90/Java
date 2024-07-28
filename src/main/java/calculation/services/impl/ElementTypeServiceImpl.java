package calculation.services.impl;

import calculation.repository.entity.ElementType;
import calculation.repository.service.interfaces.IElementTypeRepository;
import calculation.services.inputs.ElementTypeInput;
import calculation.services.interfaces.IElementTypeService;
import calculation.services.mapper.ElementTypeInputMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ElementTypeServiceImpl implements IElementTypeService {
    private final IElementTypeRepository elementTypeRepository;
    private final ElementTypeInputMapper elementTypeInputMapper;

    @Override
    public ElementType persistElementType(ElementTypeInput elementTypeInput) {
        return elementTypeRepository.save(
                elementTypeInputMapper.mapToElementType(elementTypeInput)
        );
    }

    @Override
    public ElementType findElementTypeById(Long id) {
        return elementTypeRepository.findById(id).orElse(null);
    }

    @Override
    public ElementType findElementTypeByCode(String typeCode) {
        return elementTypeRepository.findElementTypeByCode(typeCode)
                .stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public void deleteElementTypeById(Long id) {
        elementTypeRepository.deleteById(id);
    }
}
