package com.demo.loader.service;

import java.util.List;

public interface UpdateService<T> {

  void update(List<T> elements);
}
