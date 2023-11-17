package com.mona.loader.service;

import com.mona.core.service.PushService;
import com.mona.loader.model.Entity;
import com.mona.loader.repository.ProviderTransactionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateTransactionService implements UpdateService<Entity<String>> {

  public final PushService pushService;
  public final ProviderTransactionRepository providerTransactionRepository;

  @Autowired
  public UpdateTransactionService(
      PushService pushService,
      ProviderTransactionRepository providerTransactionRepository
  ) {
    this.pushService = pushService;
    this.providerTransactionRepository = providerTransactionRepository;
  }

  @Override
  public void update(List<Entity<String>> entities) {
    pushService.push(entities.stream().map(Entity::getMonaObject).toList());
    providerTransactionRepository.saveTransactions(
        entities.stream().map(Entity::getProviderObject).toList()
    );
  }
}
