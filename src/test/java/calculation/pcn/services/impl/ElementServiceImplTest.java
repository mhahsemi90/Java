package calculation.pcn.services.impl;

import calculation.assertclass.ElementAssert;
import calculation.repository.entity.Element;
import calculation.repository.entity.ElementType;
import calculation.repository.service.interfaces.ElementRepository;
import calculation.services.impl.ElementServiceImpl;
import calculation.services.inputs.ElementInput;
import calculation.services.mapper.ElementInputMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ElementServiceImplTest {
    @Mock
    private ElementRepository elementRepository;

    @Mock()
    private ElementInputMapper elementInputMapper;

    @InjectMocks
    private ElementServiceImpl elementService;

    @Test
    void findElementById() {
        when(elementRepository.findById(0L))
                .then(invocation -> Optional.empty());
        ElementAssert.assertThat(elementService.findElementById(0L)).isEqualTo(null);
        ElementType person = new ElementType(1L, "PERSON", "person", new ArrayList<>());
        Element foundedElement = new Element(1L, "Test", person, new ArrayList<>(), new ArrayList<>());
        when(elementRepository.findById(1L))
                .then(invocation -> Optional.of(foundedElement));
        ElementAssert.assertThat(elementService.findElementById(1L)).isInstanceOf(Element.class);
        ElementAssert.assertThat(elementService.findElementById(1L)).isSameAs(foundedElement);
    }

    @Test
    void findElementByVrId() {
        List<Element> foundedElementList = new ArrayList<>();
        ElementType person = new ElementType(1L, "PERSON", "person", new ArrayList<>());
        Element foundedElement = new Element(0L, "Test", person, new ArrayList<>(), new ArrayList<>());
        foundedElementList.add(foundedElement);
        when(elementRepository.findElementByVrIdAndByElementTypeCode("Test", "PERSON"))
                .then(invocation -> foundedElementList);
        ElementAssert.assertThat(elementService.findElementByVrIdAndByElementType("Test", "PERSON")).isSameAs(foundedElement);
    }

    @Test
    void persistNewElement() {
        ElementInput elementInput = new ElementInput(null, "Test", "PERSON");
        ElementType person = new ElementType(1L, "PERSON", "person", new ArrayList<>());
        Element elementMapped = new Element(null, "Test", person, new ArrayList<>(), new ArrayList<>());
        Element elementPersisted = new Element(1L, "Test", person, new ArrayList<>(), new ArrayList<>());
        when(elementInputMapper.mapToElement(elementInput))
                .then(invocation -> elementMapped);
        when(elementRepository.save(elementMapped))
                .then(invocation -> elementPersisted);
        ElementAssert.assertThat(elementService.persistElement(elementInput)).isSameAs(elementPersisted);
    }
}