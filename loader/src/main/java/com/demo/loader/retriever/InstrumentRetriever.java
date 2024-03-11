package com.demo.loader.retriever;

import com.demo.core.api.Retriever;
import com.demo.loader.model.Entity;
import com.demo.loader.provider.InstrumentProvider;
import com.demo.loader.service.UpdateInstrumentService;
import java.time.Instant;
import java.util.List;

public class InstrumentRetriever extends RetrieverDecorator {

  public final InstrumentProvider instrumentProvider;
  public final UpdateInstrumentService updateService;
  public final Instant lastSync;

  public InstrumentRetriever(
      InstrumentProvider instrumentProvider,
      Instant lastSync,
      UpdateInstrumentService updateService
  ) {
    this(instrumentProvider, lastSync, updateService, null);
  }

  public InstrumentRetriever(
      InstrumentProvider instrumentProvider,
      Instant lastSync,
      UpdateInstrumentService updateService,
      Retriever retriever
  ) {
    super(retriever);
    this.lastSync = lastSync;
    this.instrumentProvider = instrumentProvider;
    this.updateService = updateService;
  }

  @Override
  public void execute() {
    super.execute();
    List<String> instruments = instrumentProvider.getInstruments(lastSync);
    updateService.update(
        instruments.stream().map(
            s -> Entity.<String>builder().providerObject(s)
                .monaObject(instrumentProvider.toInstrument(s))
                .build()
        ).toList()
    );

  }
}
