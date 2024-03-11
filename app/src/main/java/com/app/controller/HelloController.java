package com.app.controller;

import com.demo.core.api.Retriever;
import com.demo.core.api.RetrieverFactory;
import java.util.stream.IntStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

  @Autowired
  RetrieverFactory factory;

  @Autowired
  TaskExecutor taskExecutor;

  @GetMapping("/hello/{institution}")
  public String hello(@PathVariable(required = true) String institution) {
    Retriever retriever = factory.getRetriever("Carlos Huga", institution, "caramelote");
    IntStream.range(0,1).forEach( i -> taskExecutor.execute(retriever::execute));
    return "Greetings from Spring Boot!";
  }
}
