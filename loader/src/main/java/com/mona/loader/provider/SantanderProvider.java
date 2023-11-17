package com.mona.loader.provider;

import java.time.Instant;
import java.util.List;
import java.util.stream.IntStream;

public class SantanderProvider implements InstrumentProvider, TransactionProvider {

  @Override
  public List<String> getInstruments(Instant date) {
    System.out.println("Getting Santander Instruments");
    return IntStream.range(0, 1).boxed().map(s -> "providerInstrument").toList();
  }

  @Override
  public String toInstrument(String instrument) {
    return "monaInstrument";
  }

  @Override
  public List<String> getTransactions(Instant date) {
    System.out.println("Getting Santander Transactions");
    return IntStream.range(0, 3).boxed().map(s -> "providerTransaction").toList();
  }

  @Override
  public String toTransaction(String instrument) {
    return "monaTransaction";
  }
}
