package com.example.integrationtest.repository;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.example.integrationtest.entity.PersonEntity;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Propagation;

@ActiveProfiles(profiles = {"h2"})
@DataJpaTest
//@Transactional(propagation = Propagation.NOT_SUPPORTED)
//@AutoConfigureTestDatabase(replace=Replace.NONE)
class PersonEntityRepositoryTest {

  @Autowired
  private PersonRepository personRepository;

  @Autowired
  private TestEntityManager manager;
  //https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/test/autoconfigure/orm/jpa/TestEntityManager.html

  @Test
  @DisplayName("Save person")
  void savePerson() {

    PersonEntity expected = new PersonEntity();
    expected.setId("98765");
    expected.setPersonName("name");
    expected.setLastName("lastName");
    expected.setDocument("personal document");
    expected.setAge(20);
    expected.setProfession("developer");

    personRepository.save(expected);

    PersonEntity actual = manager.getEntityManager().find(PersonEntity.class, expected.getId());

    assertNotNull(actual);
    assertAll(
        () -> assertEquals(expected.getId(), actual.getId()),
        () -> assertEquals(expected.getPersonName(), actual.getPersonName()),
        () -> assertEquals(expected.getLastName(), actual.getLastName()),
        () -> assertEquals(expected.getDocument(), actual.getDocument()),
        () -> assertEquals(expected.getProfession(), actual.getProfession()),
        () -> assertEquals(expected.getAge(), actual.getAge())
    );
  }

  @Test
  @DisplayName("Find person byName")
  void findByName() {

    PersonEntity expected = new PersonEntity();
    expected.setId("98765");
    expected.setPersonName("name_testing");
    expected.setLastName("lastName");
    expected.setDocument("personal document");
    expected.setAge(20);
    expected.setProfession("developer");

    manager.persist(expected);

    List<PersonEntity> name_testing = personRepository.findByPersonName("name_testing");

    assertNotNull(name_testing);
    assertFalse(name_testing.isEmpty());
    assertNotNull(name_testing.get(0));
    assertEquals(expected.getPersonName(), name_testing.get(0).getPersonName());

  }

  @Test
  @DisplayName("Count persons")
  void countPersons() {
    Long countExpected = personRepository.count();

    Long actualCount =
        manager.getEntityManager().createQuery("select count(*) from person", Long.class)
            .getSingleResult();

    assertEquals(countExpected, actualCount);
  }

}
