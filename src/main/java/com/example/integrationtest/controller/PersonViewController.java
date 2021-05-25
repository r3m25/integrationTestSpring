package com.example.integrationtest.controller;

import static org.springframework.util.MimeTypeUtils.TEXT_HTML_VALUE;


import com.example.integrationtest.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class PersonViewController {

  private final PersonService personService;

  @GetMapping(path="/view/personas/{id}", produces = TEXT_HTML_VALUE)
  public String getPersonById(Model model, @PathVariable("id") String id) {
    model.addAttribute("persona", personService.getPerson(id));
    return "persona/detalle";
  }

}
