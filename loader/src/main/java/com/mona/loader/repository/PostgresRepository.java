package com.mona.loader.repository;

import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class PostgresRepository implements ProviderInstrumentRepository, ProviderTransactionRepository{

  @Override
  public void saveInstruments(List<String> instruments) {
    if (instruments.isEmpty()) {
      return;
    }
    System.out.println("Saving instruments to database");
    instruments.forEach(System.out::println);
  }

  @Override
  public void saveTransactions(List<String> transactions) {
    if (transactions.isEmpty()) {
      return;
    }
    System.out.println("Saving transactions to database");
    transactions.forEach(System.out::println);
  }
}
