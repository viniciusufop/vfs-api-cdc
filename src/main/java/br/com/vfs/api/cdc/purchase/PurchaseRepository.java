package br.com.vfs.api.cdc.purchase;

import org.springframework.data.repository.CrudRepository;

public interface PurchaseRepository extends CrudRepository<Purchase, Long> {
}
