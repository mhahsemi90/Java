package com.fanap.hcm.core.hcmcore.pcn.services.impl;

import com.fanap.hcm.core.hcmcore.pcn.repository.entity.Person;
import com.fanap.hcm.core.hcmcore.pcn.repository.service.interfaces.IPersonRepository;
import com.fanap.hcm.core.hcmcore.pcn.services.inputs.PersonInput;
import com.fanap.hcm.core.hcmcore.pcn.services.interfaces.PersonService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PersonServiceImpl implements PersonService {

    private final IPersonRepository IPersonRepository;
    private final ModelMapper modelMapper;

    public PersonServiceImpl(IPersonRepository IPersonRepository, ModelMapper modelMapper) {
        this.IPersonRepository = IPersonRepository;
        this.modelMapper = modelMapper;
        TypeMap<PersonInput, Person> personDtoPersonTypeMap = modelMapper
                .createTypeMap(PersonInput.class, Person.class);
        personDtoPersonTypeMap.addMappings(mapper -> mapper.skip(Person::setId));
    }

    @Override
    public Person findPersonById(Long id) {
        return IPersonRepository.findById(id).orElse(new Person(0L, "Not Found", new ArrayList<>(), new ArrayList<>()));
    }

    @Override
    public Person findPersonByVrId(String vrId) {
        return IPersonRepository.findPersonByVrId(vrId).orElse(new Person());
    }

    @Override
    public Person persistNewPerson(PersonInput personInput) {
        return IPersonRepository.save(
                modelMapper.map(personInput, Person.class)
        );
    }
}
