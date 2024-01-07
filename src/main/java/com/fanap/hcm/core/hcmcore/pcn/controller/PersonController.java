package com.fanap.hcm.core.hcmcore.pcn.controller;

import com.fanap.hcm.core.hcmcore.pcn.repository.entity.Person;
import com.fanap.hcm.core.hcmcore.pcn.services.inputs.PersonInput;
import com.fanap.hcm.core.hcmcore.pcn.services.interfaces.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class PersonController {
    private final PersonService personService;

    @QueryMapping
    Person findPerson(@Argument Long id) {
        return personService.findPersonById(id);
    }

    @QueryMapping
    Person findPersonByVrId(@Argument String vrId) {
        return personService.findPersonByVrId(vrId);
    }

    @MutationMapping
    Person persistNewPerson(@Argument PersonInput personInput) {
        return personService.persistNewPerson(personInput);
    }
}
