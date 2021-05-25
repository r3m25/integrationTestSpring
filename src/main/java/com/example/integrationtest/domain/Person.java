package com.example.integrationtest.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Person {

  private String id;
  private String name;
  private String lastName;
  private String document;
  private int age;
  private String profession;

}
