package calculation.services.impl;

import calculation.repository.entity.Element;
import calculation.repository.service.interfaces.ElementRepository;
import calculation.services.inputs.ElementInput;
import calculation.services.interfaces.ElementService;
import calculation.services.mapper.ElementInputMapper;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
public class ElementServiceImpl implements ElementService {

    private final ElementRepository elementRepository;
    private final ElementInputMapper elementInputMapper;

    @Override
    public Element persistElement(ElementInput elementInput) {
        return elementRepository.save(
                elementInputMapper.mapToElement(elementInput)
        );
    }

    @Override
    public Element findElementById(Long id) {
        return elementRepository.findById(id).orElse(null);
    }

    @Override
    @Async
    @Transactional
    public CompletableFuture<Element> findElementReferenceById(Long id) {
        return CompletableFuture.completedFuture(elementRepository.getReferenceById(id));
    }

    @Override
    public Element findElementByVrIdAndByElementType(String vrId, String elementTypeCode) {
        return elementRepository.findElementByVrIdAndByElementTypeCode(vrId, elementTypeCode)
                .stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public void deleteElementById(Long id) {
        elementRepository.deleteById(id);
    }
}
