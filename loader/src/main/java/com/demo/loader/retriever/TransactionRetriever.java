package com.demo.loader.retriever;

import com.demo.core.api.Retriever;
import com.demo.loader.model.Entity;
import com.demo.loader.provider.TransactionProvider;
import com.demo.loader.service.UpdateTransactionService;
import java.time.Instant;
import java.util.List;

public class TransactionRetriever extends RetrieverDecorator {

  public final TransactionProvider transactionProvider;
  public final UpdateTransactionService updateService;
  public final Instant lastSync;

  public TransactionRetriever(
      TransactionProvider transactionProvider,
      Instant lastSync,
      UpdateTransactionService updateService
  ) {
    this(transactionProvider, lastSync, updateService, null);
  }

  public TransactionRetriever(
      TransactionProvider transactionProvider,
      Instant lastSync,
      UpdateTransactionService updateService,
      Retriever retriever
  ) {
    super(retriever);
    this.lastSync = lastSync;
    this.transactionProvider = transactionProvider;
    this.updateService = updateService;
  }

  @Override
  public void execute() {
    super.execute();
    List<String> transactions = transactionProvider.getTransactions(lastSync);
    updateService.update(
        transactions.stream().map(
            s -> Entity.<String>builder().providerObject(s)
                .monaObject(transactionProvider.toTransaction(s))
                .build()
        ).toList()
    );

  }
}
