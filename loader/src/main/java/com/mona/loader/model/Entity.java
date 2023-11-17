package com.mona.loader.model;

import lombok.Builder;
import lombok.Data;

@Builder(toBuilder = true)
@Data
public class Entity<T> {

  private final String providerObjectId;
  private final String providerObject;
  private final T monaObject;
}
