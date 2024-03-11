package com.demo.loader.retriever;

import com.demo.core.api.Retriever;

public abstract class RetrieverDecorator extends Retriever {

  public Retriever decoratedRetriever;

  public RetrieverDecorator(Retriever retriever) {
    this.decoratedRetriever = retriever;
  }

  @Override
  public void execute() {
    try {
      Thread.sleep(2000L);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    if (decoratedRetriever == null) {
      return;
    }
    decoratedRetriever.execute();
  }
}
