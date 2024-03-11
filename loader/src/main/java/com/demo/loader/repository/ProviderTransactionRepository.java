package com.demo.loader.repository;

import java.util.List;

public interface ProviderTransactionRepository {

  void saveTransactions(List<String> transactions);

}
