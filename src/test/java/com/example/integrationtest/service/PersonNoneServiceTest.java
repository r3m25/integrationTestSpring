package com.example.integrationtest.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


import com.example.integrationtest.domain.Person;
import com.example.integrationtest.entity.PersonEntity;
import com.example.integrationtest.repository.PersonRepository;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(profiles = "h2")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class PersonNoneServiceTest {

  @MockBean private PersonRepository personRepository;

  @Autowired private PersonService personService;

  @Test
  @DisplayName("Should return a person")
  void getPerson() {

    when(personRepository.findById(anyString())).thenReturn(Optional.of(buildPerson()));

    Person person = personService.getPerson("101");

    assertNotNull(person);
  }

  private PersonEntity buildPerson() {
    PersonEntity personEntity = new PersonEntity();
    personEntity.setId("101");
    personEntity.setPersonName("nombre_test");
    personEntity.setLastName("apellido_test");
    personEntity.setDocument("0000001");
    personEntity.setAge(21);
    personEntity.setProfession("profesion_01");
    return personEntity;
  }

}