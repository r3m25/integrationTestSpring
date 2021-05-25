package com.example.integrationtest.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

@ActiveProfiles(profiles = "h2")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PersonRestSqlControllerTest {

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate testRestTemplate;

  @Sql({ "drop_table.sql", "create_schema.sql", "insert_data.sql" })
  @Test
  @DisplayName("Return persons get for object")
  void getPersonsGetForObject() {

    Integer countPersons =
        testRestTemplate.getForObject("http://localhost:" + port + "/person/total", Integer.class);

    assertNotNull(countPersons);
    assertEquals(3, countPersons);
  }

}