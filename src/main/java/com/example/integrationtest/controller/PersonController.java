package com.example.integrationtest.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.example.integrationtest.domain.Person;
import com.example.integrationtest.service.PersonService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PersonController {

  private final PersonService personService;

  @GetMapping(path = "/persons", produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Person>> getPersons() {
    return ResponseEntity.ok(personService.getPersons());
  }

  @GetMapping(path = "/person/{id}", produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<Person> getPerson(@PathVariable String id) {
    return ResponseEntity.ok(personService.getPerson(id));
  }

  @GetMapping(path = "/person/total", produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<Long> getCountPersons() {
    return ResponseEntity.ok(personService.totalPersons());
  }

}
