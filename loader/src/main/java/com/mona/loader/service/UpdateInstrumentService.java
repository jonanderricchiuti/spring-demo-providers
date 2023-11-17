package com.mona.loader.service;

import com.mona.core.service.PushService;
import com.mona.loader.model.Entity;
import com.mona.loader.repository.ProviderInstrumentRepository;
import com.mona.loader.repository.ProviderTransactionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateInstrumentService implements UpdateService<Entity<String>> {

  public final PushService pushService;
  public final ProviderInstrumentRepository providerInstrumentRepository;

  @Autowired
  public UpdateInstrumentService(
      PushService pushService,
      ProviderInstrumentRepository providerInstrumentRepository
  ) {
    this.pushService = pushService;
    this.providerInstrumentRepository = providerInstrumentRepository;
  }

  public void update(List<Entity<String>> entities) {
    pushService.push(entities.stream().map(Entity::getMonaObject).toList());
    providerInstrumentRepository.saveInstruments(entities.stream().map(Entity::getProviderObject).toList());
  }
}
