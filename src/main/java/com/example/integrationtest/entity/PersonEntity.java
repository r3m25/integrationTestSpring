package com.example.integrationtest.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "person")
public class PersonEntity {

  @Id
  private String id;

  @Column(name = "person_name")
  private String personName;

  @Column(name = "last_name")
  private String lastName;

  private String document;
  private int age;
  private String profession;

}
