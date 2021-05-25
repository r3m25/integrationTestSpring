package com.example.integrationtest.repository;

import com.example.integrationtest.entity.PersonEntity;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<PersonEntity, String> {

  List<PersonEntity> findByPersonName(String name);

}
