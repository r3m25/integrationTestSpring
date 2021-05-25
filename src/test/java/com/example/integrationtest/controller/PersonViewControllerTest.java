package com.example.integrationtest.controller;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


import com.example.integrationtest.domain.Person;
import com.example.integrationtest.service.PersonService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@ActiveProfiles(profiles = "slice")
@WebMvcTest(controllers = PersonViewController.class)
class PersonViewControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private PersonService personService;

  @Test
  @DisplayName("Should return view person detail")
  void getPersonById() throws Exception {

    when(personService.getPerson(anyString())).thenReturn(buildPerson());

    mockMvc.perform(get("/view/personas/{id}", "101"))
        //.andDo(print())
        .andExpect(status().isOk())
        .andExpect(model().size(1))
        .andExpect(view().name("persona/detalle"))
        .andExpect(content().contentType("text/html;charset=UTF-8"));
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