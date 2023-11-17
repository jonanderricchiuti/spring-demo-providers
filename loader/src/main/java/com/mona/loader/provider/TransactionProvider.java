package com.mona.loader.provider;

import java.time.Instant;
import java.util.List;

public interface TransactionProvider {

  List<String> getTransactions(Instant date);

  String toTransaction(String instrument);
}
