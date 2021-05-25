package com.example.integrationtest.controller;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.integrationtest.domain.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(profiles = "h2")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class PersonRestControllerTest {

  @Autowired
  private TestRestTemplate testRestTemplate;

  @Test
  @DisplayName("Return persons get for object")
  void getPersonsGetForObject() {

    Person person =
        testRestTemplate.getForObject("/person/{id}", Person.class, "101");

    assertThat(person).isNotNull()
        .isInstanceOf(Person.class)
        .hasNoNullFieldsOrProperties()
        .hasFieldOrPropertyWithValue("id", "101")
        .hasFieldOrPropertyWithValue("name", "nombre_01")
        .hasFieldOrPropertyWithValue("lastName", "apellido_01")
        .hasFieldOrPropertyWithValue("document", "0000001")
        .hasFieldOrPropertyWithValue("age", 21)
        .hasFieldOrPropertyWithValue("profession", "profesion_01");
  }

  @Test
  @DisplayName("Return persons response entity")
  void getPersonsResponseEntity() {

    ResponseEntity<Person> personaResponse =
        testRestTemplate.getForEntity("/person/{id}", Person.class, "101");

    assertThat(personaResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(personaResponse.getStatusCodeValue()).isEqualTo(200);

    assertThat(personaResponse.getBody()).isNotNull()
        .isInstanceOf(Person.class)
        .hasNoNullFieldsOrProperties()
        .hasFieldOrPropertyWithValue("id", "101")
        .hasFieldOrPropertyWithValue("name", "nombre_01")
        .hasFieldOrPropertyWithValue("lastName", "apellido_01")
        .hasFieldOrPropertyWithValue("document", "0000001")
        .hasFieldOrPropertyWithValue("age", 21)
        .hasFieldOrPropertyWithValue("profession", "profesion_01");

  }

  private static Person buildPerson() {
    return Person.builder()
        .id("123")
        .name("name")
        .lastName("lastName")
        .age(31)
        .document("document")
        .profession("profession")
        .build();
  }

}