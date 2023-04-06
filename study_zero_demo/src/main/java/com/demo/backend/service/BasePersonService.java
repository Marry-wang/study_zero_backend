package com.demo.backend.service;

import com.demo.backend.model.BasePerson;

public interface BasePersonService {

    BasePerson getBasePerson(BasePerson basePerson);

    BasePerson getPersonByName(String UserName);

}
