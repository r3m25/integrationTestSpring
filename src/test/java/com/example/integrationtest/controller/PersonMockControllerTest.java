package com.example.integrationtest.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.integrationtest.domain.Person;
import com.example.integrationtest.service.PersonService;
import java.util.Collections;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@ActiveProfiles(profiles = {"h2"})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class PersonMockControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private PersonService personService;

  @Test
  @DisplayName("Return persons")
  void getPersons() throws Exception {

    when(personService.getPersons()).thenReturn(Collections.singletonList(buildPerson()));

    mockMvc
        .perform(get("/persons"))
        .andDo(print())
        .andExpect(status().isOk());
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