package com.fanap.hcm.core.hcmcore.pcn.services.interfaces;

import com.fanap.hcm.core.hcmcore.pcn.repository.entity.Person;
import com.fanap.hcm.core.hcmcore.pcn.services.inputs.PersonInput;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    Person findPersonById(Long id);

    Person findPersonByVrId(String vrId);

    Person persistNewPerson(PersonInput personInput);
}
