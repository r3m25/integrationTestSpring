package com.example.integrationtest.service;

import static java.util.stream.StreamSupport.stream;


import com.example.integrationtest.domain.Person;
import com.example.integrationtest.entity.PersonEntity;
import com.example.integrationtest.repository.PersonRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PersonService {

    private final PersonRepository personRepository;

    public Long totalPersons() {
      return personRepository.count();
    }

    public List<Person> getPersons() {
      return stream(personRepository.findAll().spliterator(), false)
          .map(PersonService::buildPerson)
          .collect(Collectors.toList());
    }

    public Person getPerson(String id) {
    return buildPerson(personRepository.findById(id).get());
  }

    private static Person buildPerson(PersonEntity entity) {
      return Person.builder()
          .id(entity.getId())
          .name(entity.getPersonName())
          .lastName(entity.getLastName())
          .age(entity.getAge())
          .document(entity.getDocument())
          .profession(entity.getProfession())
          .build();
    }



}
