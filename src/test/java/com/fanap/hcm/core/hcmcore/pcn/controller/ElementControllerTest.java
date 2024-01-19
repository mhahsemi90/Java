package com.fanap.hcm.core.hcmcore.pcn.controller;

import com.fanap.hcm.core.hcmcore.pcn.repository.entity.Element;
import com.fanap.hcm.core.hcmcore.pcn.repository.entity.ElementType;
import com.fanap.hcm.core.hcmcore.pcn.services.inputs.ElementInput;
import com.fanap.hcm.core.hcmcore.pcn.services.interfaces.IElementService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static com.fanap.hcm.core.hcmcore.assertclass.ElementAssert.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ElementControllerTest {

    @Mock
    IElementService elementService;


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