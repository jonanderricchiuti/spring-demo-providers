package com.mona.loader.retriever;

import com.mona.core.api.Retriever;
import com.mona.core.api.RetrieverFactory;
import com.mona.loader.provider.BBVAProvider;
import com.mona.loader.provider.SantanderProvider;
import com.mona.loader.service.UpdateInstrumentService;
import com.mona.loader.service.UpdateTransactionService;
import java.time.Instant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RetrieverFactoryImp implements RetrieverFactory {

  public UpdateTransactionService updateTransactionService;
  public UpdateInstrumentService updateInstrumentService;


  @Autowired
  public RetrieverFactoryImp(
      UpdateTransactionService updateTransactionService,
      UpdateInstrumentService updateInstrumentService
  ) {
    this.updateTransactionService = updateTransactionService;
    this.updateInstrumentService = updateInstrumentService;
  }

  @Override
  public Retriever getRetriever(String user, String institution, String credentials) {
    return
        switch (institution) {
          case "bbva" -> {
            BBVAProvider BBVAProvider = new BBVAProvider();
            yield new TransactionRetriever(
                BBVAProvider,
                Instant.now(),
                this.updateTransactionService,
                new InstrumentRetriever(
                    BBVAProvider,
                    Instant.now(),
                    this.updateInstrumentService
                )
            );
          }
          case "santander" -> new TransactionRetriever(new SantanderProvider(), Instant.now(),
              this.updateTransactionService,
              new InstrumentRetriever(new SantanderProvider(), Instant.now(),
                  this.updateInstrumentService)
          );
          default -> null;
        };
  }
}
