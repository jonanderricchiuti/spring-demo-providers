package com.demo.core.service;

import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class PushService {

  public void push(List<String> elements) {
    if (elements.isEmpty()) {
      return;
    }
    System.out.println("pushing ...");
    elements.forEach(System.out::println);
  }
}
