package com.demo.core.api;

public interface RetrieverFactory {
  Retriever getRetriever(String user, String institution, String credentials);
}
