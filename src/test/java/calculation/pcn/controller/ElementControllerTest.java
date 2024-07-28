package calculation.pcn.controller;

import calculation.controller.ElementController;
import calculation.repository.entity.Element;
import calculation.repository.entity.ElementType;
import calculation.services.inputs.ElementInput;
import calculation.services.interfaces.ElementService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static calculation.assertclass.ElementAssert.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ElementControllerTest {

    @Mock
    ElementService elementService;


    @InjectMocks
    ElementController elementController;

    @Test
    void findElementById() {
        when(elementService.findElementById(0L))
                .then(invocation -> null);
        assertThat(elementController.findElementById(0L)).isEqualTo(null);
        ElementType person = new ElementType(1L, "PERSON", "person", new ArrayList<>());
        Element foundedElement = new Element(1L, "Test", person, new ArrayList<>(), new ArrayList<>());
        when(elementService.findElementById(1L))
                .then(invocation -> foundedElement);
        assertThat(elementController.findElementById(1L)).isInstanceOf(Element.class);
        assertThat(elementController.findElementById(1L)).isSameAs(foundedElement);
    }

    @Test
    void findElementByVrId() {
        ElementType person = new ElementType(1L, "PERSON", "person", new ArrayList<>());
        Element foundedElement = new Element(1L, "Test", person, new ArrayList<>(), new ArrayList<>());
        when(elementService.findElementByVrIdAndByElementType("Test", "PERSON"))
                .then(invocation -> foundedElement);
        assertThat(elementController.findElementByVrIdAndByElementType("Test", "PERSON"))
                .isInstanceOf(Element.class);
        assertThat(elementController.findElementByVrIdAndByElementType("Test", "PERSON"))
                .isSameAs(foundedElement);
    }

    @Test
    void persistNewElement() {
        ElementInput elementInput = new ElementInput(null, "Test", "PERSON");
        ElementType person = new ElementType(1L, "PERSON", "person", new ArrayList<>());
        Element elementPersisted = new Element(1L, "Test", person, new ArrayList<>(), new ArrayList<>());
        when(elementService.persistElement(elementInput))
                .then(invocation -> elementPersisted);
        assertThat(elementController.persistElement(elementInput))
                .isSameAs(elementPersisted);
    }
}