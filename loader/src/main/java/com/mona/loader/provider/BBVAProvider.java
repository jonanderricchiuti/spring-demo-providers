package com.mona.loader.provider;

import java.time.Instant;
import java.util.List;
import java.util.stream.IntStream;

public class BBVAProvider implements InstrumentProvider, TransactionProvider {

  boolean token;

  @Override
  public List<String> getInstruments(Instant date) {
    updateToken();
    System.out.println("Getting BBVA Instruments");
    return IntStream.range(0, 1).boxed().map(s -> "providerInstrument").toList();
  }

  @Override
  public String toInstrument(String instrument) {
    return "monaInstrument";
  }

  @Override
  public List<String> getTransactions(Instant date) {
    updateToken();
    System.out.println("Getting BBVA Transactions");
    return IntStream.range(0, 5).boxed().map(s -> "providerTransaction").toList();
  }

  @Override
  public String toTransaction(String instrument) {
    return "monaTransaction";
  }

  private void updateToken() {
    if (!token) {
      System.out.println("token needed, asking for one ...");
      token = true;
    }
  }
}
